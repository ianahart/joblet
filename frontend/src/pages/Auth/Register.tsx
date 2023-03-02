import { Box } from '@chakra-ui/react';
import registerBG from '../../images/register.png';

const Register = () => {
  return (
    <Box
      minH="100vh"
      backgroundImage={`url(${registerBG})`}
      backgroundPosition="center"
      backgroundSize="cover"
    >
      Register page
    </Box>
  );
};

export default Register;
