import { Box, Button, Flex, Text } from '@chakra-ui/react';
import FormContainer from '../../components/Form/FormContainer';
import FormInput from '../../components/Form/FormInput';
import registerBG from '../../images/register.png';
import { ICreateAccountForm } from '../../interfaces';
import { createAccountState } from '../../data/';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { http } from '../../helpers/utils';
import RegisterFormLabel from '../../components/Register/RegisterFormLabel';
import { AxiosError } from 'axios';

const Register = () => {
  const navigate = useNavigate();
  const [form, setForm] = useState(createAccountState);
  const [error, setError] = useState('');

  const updateField = (name: string, value: string, attribute: string) => {
    setForm((prevState) => ({
      ...prevState,
      [name]: { ...prevState[name as keyof ICreateAccountForm], [attribute]: value },
    }));
  };

  const checkForErrors = () => {
    let errors = false;
    for (const [_, field] of Object.entries(form)) {
      if (field.error.length || !field.value.length) {
        errors = true;
      }
    }
    return errors;
  };

  const clearErrors = () => {
    for (const [fieldName, _] of Object.entries(form)) {
      updateField(fieldName, '', 'error');
    }
  };

  const handleOnSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    clearErrors();
    setError('');

    if (checkForErrors()) {
      setError('Please make sure the form has no errors.');
      return;
    }
    await createAccount();
  };

  const createAccount = async () => {
    try {
      await http.post('/auth/register', {
        firstName: form.firstName.value,
        lastName: form.lastName.value,
        email: form.email.value,
        password: form.password.value,
        role: 'USER',
      });
      navigate('/login');
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        if (err.response.status === 400) {
          setError(err.response.data.message);
        }
      }
    }
  };

  return (
    <Box
      minH="100vh"
      backgroundImage={`url(${registerBG})`}
      backgroundPosition="center"
      backgroundSize="cover"
    >
      <Box pt="5rem">
        <FormContainer
          title="Create Account"
          width={['95%', '95%', '650px']}
          minH="500px"
        >
          {error && (
            <Text color="error.primary" fontSize="0.85rem">
              {error}
            </Text>
          )}
          <form onSubmit={handleOnSubmit}>
            <Flex
              my="1.5rem"
              flexDir={['column', 'column', 'row']}
              m="1rem"
              textAlign="left"
              space-around
              justifyContent="space-around"
            >
              <RegisterFormLabel text="Name" />
              <FormInput
                updateField={updateField}
                value={form.firstName.value}
                error={form.firstName.error}
                name={form.firstName.name}
                type={form.firstName.type}
                errorField="First name"
                width="90%"
                htmlFor="firstName"
                label="First Name"
              />
              <FormInput
                updateField={updateField}
                value={form.lastName.value}
                error={form.lastName.error}
                name={form.lastName.name}
                type={form.lastName.type}
                errorField="Last name"
                width="90%"
                htmlFor="lastName"
                label="Last Name"
              />
            </Flex>
            <Flex my="1.5rem" mx="1rem" flexDir={['column', 'column', 'row']}>
              <RegisterFormLabel text="Email"></RegisterFormLabel>
              <FormInput
                updateField={updateField}
                value={form.email.value}
                error={form.email.error}
                name={form.email.name}
                type={form.email.type}
                errorField="Email"
                width="90%"
                htmlFor="email"
                label=""
              />
            </Flex>
            <Flex my="1.5rem" mx="1rem" flexDir={['column', 'column', 'row']}>
              <RegisterFormLabel text="Password" />
              <FormInput
                updateField={updateField}
                value={form.password.value}
                error={form.password.error}
                name={form.password.name}
                type={form.password.type}
                errorField="Password"
                width="90%"
                htmlFor="password"
                label=""
              />
            </Flex>
            <Box py="1rem">
              <Button width="50%" type="submit" colorScheme="teal">
                Create
              </Button>
            </Box>
          </form>
        </FormContainer>
      </Box>
    </Box>
  );
};

export default Register;
