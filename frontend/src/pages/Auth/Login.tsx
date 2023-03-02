import { Box } from '@chakra-ui/react';
import loginBG from '../../images/login.png';

const Login = () => {
  return (
    <Box
      minH="100vh"
      backgroundImage={`url(${loginBG})`}
      backgroundPosition="center"
      backgroundSize="cover"
    >
      Login page
    </Box>
  );
};

export default Login;
