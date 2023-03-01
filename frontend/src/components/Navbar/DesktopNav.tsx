import { Flex } from '@chakra-ui/react';
import NavigationLink from './NavigationLink';

const DesktopNav = () => {
  return (
    <Flex display={['none', 'none', 'flex']}>
      <NavigationLink theme="light" text="Company Reviews" to="company-reviews" />
      <NavigationLink theme="light" text="Create Account" to="register" />
      <NavigationLink theme="light" text="Sign in" to="login" />
    </Flex>
  );
};

export default DesktopNav;
