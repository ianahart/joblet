import { Flex, Text } from '@chakra-ui/react';
import { AxiosError } from 'axios';
import { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { UserContext } from '../../context/user';
import { http } from '../../helpers/utils';
import { IUserContext } from '../../interfaces';
import NavigationLink from './NavigationLink';

const DesktopNav = () => {
  const navigate = useNavigate();
  const { logout, user } = useContext(UserContext) as IUserContext;

  const handleOnLogout = async () => {
    try {
      await http.post('/auth/logout', {});
      logout();
      navigate('/login');
    } catch (err: unknown | AxiosError) {
      console.log(err);
      return;
    }
  };

  return (
    <Flex display={['none', 'none', 'flex']}>
      <NavigationLink theme="light" text="Company Reviews" to="company-reviews" />
      {user.id === 0 && (
        <NavigationLink theme="light" text="Create Account" to="register" />
      )}
      {user.id === 0 && <NavigationLink theme="light" text="Sign in" to="login" />}
      {user.id !== 0 && (
        <Text
          onClick={handleOnLogout}
          p="0.5rem"
          role="button"
          color="light.primary"
          fontWeight="bold"
        >
          Logout
        </Text>
      )}
    </Flex>
  );
};

export default DesktopNav;
