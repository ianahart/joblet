import { Box, Button, Flex } from '@chakra-ui/react';
import FormContainer from '../../components/Form/FormContainer';
import loginBG from '../../images/login.png';
import { ILoginForm } from '../../interfaces';
import { loginState } from '../../data';
import { useState } from 'react';
import { Link as RouterLink } from 'react-router-dom';
import FormInput from '../../components/Form/FormInput';
import { AxiosError } from 'axios';
import { http } from '../../helpers/utils';
const Login = () => {
  const [form, setForm] = useState(loginState);
  const [error, setError] = useState('');

  const updateField = (name: string, value: string, attribute: string) => {
    setForm((prevState) => ({
      ...prevState,
      [name]: { ...prevState[name as keyof ILoginForm], [attribute]: value },
    }));
  };

  const checkforErrors = () => {
    let errors = false;

    for (const [_, field] of Object.entries(form)) {
      if (field.error.length || field.value.trim().length === 0) {
        errors = true;
      }
    }

    return errors;
  };

  const handleOnSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    checkforErrors();
    if (checkforErrors()) {
      setError('Please make sure there are no errors');
      return;
    }
    await signIn();
  };

  const signIn = async () => {
    try {
      const response = await http.post('/auth/login', {
        email: form.email.value,
        password: form.password.value,
      });
      console.log(response);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        console.log(err);
      }
    }
  };

  return (
    <Box
      minH="100vh"
      backgroundImage={`url(${loginBG})`}
      backgroundPosition="center"
      backgroundSize="cover"
    >
      <Box pt="5rem">
        <FormContainer title="Sign In" width={['95%', '95%', '500px']} minH="500px">
          <Box as="main" py="5rem">
            <form onSubmit={handleOnSubmit}>
              <FormInput
                updateField={updateField}
                value={form.email.value}
                error={form.email.error}
                name={form.email.name}
                type={form.email.type}
                errorField="Email"
                width="90%"
                htmlFor="email"
                label="Email"
              />
              <FormInput
                updateField={updateField}
                value={form.password.value}
                error={form.password.error}
                name={form.password.name}
                type={form.password.type}
                errorField="Password"
                width="90%"
                htmlFor="password"
                label="Password"
              />
              <Flex color="green.primary" justifyContent="flex-end" m="2.5rem">
                <RouterLink to="/forgot-password">Forgot password?</RouterLink>
              </Flex>
              <Box>
                <Button type="submit" width="80%" colorScheme="teal">
                  Sign In
                </Button>
              </Box>
            </form>
          </Box>
        </FormContainer>
      </Box>
    </Box>
  );
};

export default Login;
