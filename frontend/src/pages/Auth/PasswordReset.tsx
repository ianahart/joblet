import { Flex } from '@chakra-ui/react';
import { useState } from 'react';
import Form from '../../components/PasswordReset/Form';
import Success from '../../components/PasswordReset/Success';

const PasswordReset = () => {
  const [success, setSuccess] = useState(false);

  const handleSuccess = (succ: boolean) => {
    setSuccess(succ);
  };

  return (
    <Flex
      as="main"
      justifyContent="center"
      mt="8rem"
      width={['95%', '95%', '500px']}
      mx="auto"
    >
      {!success ? <Form handleSuccess={handleSuccess} /> : <Success />}
    </Flex>
  );
};
export default PasswordReset;
