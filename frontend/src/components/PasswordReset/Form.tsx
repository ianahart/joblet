import { Box, Button, Flex, Text } from '@chakra-ui/react';
import { useState } from 'react';
import { passwordResetState } from '../../data';
import FormInput from '../Form/FormInput';
import { IPasswordResetForm } from '../../interfaces';
import Header from './Header';
import { useSearchParams } from 'react-router-dom';
import { AxiosError } from 'axios';
import { http } from '../../helpers/utils';

interface IFormProps {
  handleSuccess: (succ: boolean) => void;
}

const Form = ({ handleSuccess }: IFormProps) => {
  const [form, setForm] = useState(passwordResetState);
  const [searchParams] = useSearchParams();
  const [error, setError] = useState('');
  const uid = searchParams.get('uid');
  const token = searchParams.get('token');

  const updateField = (name: string, value: string, attribute: string) => {
    setForm((prevState) => ({
      ...prevState,
      [name]: { ...prevState[name as keyof IPasswordResetForm], [attribute]: value },
    }));
  };

  const checkForErrors = () => {
    let errors = false;
    for (const [_, field] of Object.entries(form)) {
      const { error, value } = field;
      if (error.length || value.trim().length === 0) {
        errors = true;
      }
    }
    return errors;
  };

  const handleOnSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setError('');
    if (checkForErrors()) {
      return;
    }
    resetPassword();
  };

  const resetPassword = async () => {
    try {
      await http.post('/auth/reset-password', {
        id: uid,
        token,
        oldPassword: form.oldPassword.value,
        newPassword: form.newPassword.value,
        confirmPassword: form.confirmPassword.value,
      });
      handleSuccess(true);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        setError(err.response?.data?.message);
      }
    }
  };

  return (
    <Box>
      <Header
        icon="key"
        text="Your new password must be different to previously used passwords."
        heading="Set new password"
      />
      <form onSubmit={handleOnSubmit}>
        {error && (
          <Text fontSize="0.85rem" color="error.primary" textAlign="center" my="1rem">
            {error}
          </Text>
        )}
        <FormInput
          updateField={updateField}
          value={form.oldPassword.value}
          error={form.oldPassword.error}
          name={form.oldPassword.name}
          type={form.oldPassword.type}
          errorField="Current password"
          width="90%"
          htmlFor="oldPassword"
          label="Current Password"
        />
        <FormInput
          updateField={updateField}
          value={form.newPassword.value}
          error={form.newPassword.error}
          name={form.newPassword.name}
          type={form.newPassword.type}
          errorField="New password"
          width="90%"
          htmlFor="newPassword"
          label="New Password"
        />
        <FormInput
          updateField={updateField}
          value={form.confirmPassword.value}
          error={form.confirmPassword.error}
          name={form.confirmPassword.name}
          type={form.confirmPassword.type}
          errorField="Confirm password"
          width="90%"
          htmlFor="confirmPassword"
          label="Confirm Password"
        />
        <Flex justifyContent="center" my="2rem">
          <Button type="submit" colorScheme="teal" width="90%">
            Reset Password
          </Button>
        </Flex>
      </form>
    </Box>
  );
};

export default Form;
