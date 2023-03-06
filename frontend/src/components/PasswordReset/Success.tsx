import { Box, Flex } from '@chakra-ui/react';
import { AiOutlineArrowLeft } from 'react-icons/ai';
import { Link as RouterLink } from 'react-router-dom';
import PasswordResetHeader from './PasswordResetHeader';

const Success = () => {
  return (
    <Flex flexDir="column" as="main">
      <PasswordResetHeader
        heading="Password reset"
        icon="check"
        text="Your password has been successfully reset."
      />
      <Flex justifyContent="center" color="text.primary">
        <Box fontSize="1.5rem">
          <AiOutlineArrowLeft />
        </Box>
        <RouterLink to="/login">Back to sign in</RouterLink>
      </Flex>
    </Flex>
  );
};

export default Success;
