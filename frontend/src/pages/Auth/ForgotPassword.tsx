import {
  Box,
  Button,
  Flex,
  FormControl,
  FormLabel,
  Heading,
  Image,
  Input,
  Text,
} from '@chakra-ui/react';
import spinner from '../../images/loader.svg';
import { Link as RouterLink } from 'react-router-dom';
import { AiOutlineKey, AiOutlineArrowLeft, AiOutlineMail } from 'react-icons/ai';
import { useState } from 'react';
import { AxiosError } from 'axios';
import { http } from '../../helpers/utils';

const ForgotPassword = () => {
  const [email, setEmail] = useState('');
  const [error, setError] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [success, setSuccess] = useState(false);

  const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(e.target.value);
  };

  const handleOnSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setError('');
    if (email.trim().length === 0) return;
    sendEmail();
  };

  const sendEmail = async () => {
    try {
      setIsLoading(true);
      const response = await http.post('/auth/send-email', { email });
      setIsLoading(false);
      setSuccess(true);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        if (err.response.status === 404) {
          setError(err.response.data.message);
          setIsLoading(false);
        }
      }
    }
  };

  return (
    <Flex as="main" flexDir="column" mt="8rem" alignItems="center">
      <Box
        p="0.5rem"
        display="flex"
        justifyContent="center"
        as="section"
        width={['95%', '95%', '500px']}
      >
        <form onSubmit={handleOnSubmit} style={{ width: '80%' }}>
          <Box>
            <Flex color="green.secondary" fontSize="3rem" justifyContent="center">
              <Flex
                opacity="0.75"
                alignItems="center"
                justifyContent="center"
                flexDir="column"
                bg="green.primary"
                borderRadius="50%"
                width="60px"
                height="60px"
              >
                {!success ? <AiOutlineKey /> : <AiOutlineMail />}
              </Flex>
            </Flex>
            <Box color="black.primary" textAlign="center" my="1rem">
              {!success ? (
                <Heading as="h1">Forgot Password?</Heading>
              ) : (
                <Heading as="h1">Check your email</Heading>
              )}
            </Box>
            <Box color="text.primary" textAlign="center" my="1rem">
              {!success ? (
                <Text>No worries, we'll send you reset instructions.</Text>
              ) : (
                <Text>We sent a password reset link to {email}</Text>
              )}
            </Box>
            {error && (
              <Text fontSize="0.85rem" color="error.primary" textAlign="center" my="1rem">
                {error}
              </Text>
            )}
            {isLoading && (
              <Flex justifyContent="center">
                <Image
                  width="50px"
                  height="50px"
                  src={spinner}
                  alt="a loading spinnner"
                />
              </Flex>
            )}
            {!success ? (
              <Box>
                <FormControl>
                  <FormLabel color="text.primary">Email</FormLabel>
                  <Input onChange={handleOnChange} placeholder="Enter your email" />
                </FormControl>
                <Flex justifyContent="center" my="3rem">
                  <Button type="submit" width="100%" colorScheme="teal">
                    {' '}
                    Reset Password
                  </Button>
                </Flex>
              </Box>
            ) : (
              <Flex alignItems="center" justifyContent="center">
                <Text
                  color="text.primary"
                  mx="0.25rem"
                  fontSize="0.85rem"
                  textAlign="center"
                >
                  Didn't receive the email?
                </Text>
                <Text
                  mx="0.25rem"
                  role="button"
                  color="green.primary"
                  fontSize="0.85rem"
                  fontWeight="bold"
                  onClick={sendEmail}
                >
                  Click to resend
                </Text>
              </Flex>
            )}
            <Flex
              fontSize="0.9rem"
              color="text.primary"
              justifyContent="center"
              alignItems="center"
            >
              <AiOutlineArrowLeft />
              <RouterLink to="/login">Back to sign in</RouterLink>
            </Flex>
          </Box>
        </form>
      </Box>
    </Flex>
  );
};

export default ForgotPassword;
