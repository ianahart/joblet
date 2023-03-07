import { Flex, Box } from '@chakra-ui/react';
import { Link as RouterLink } from 'react-router-dom';

interface IProfileNavigationLinkProps {
  to: string;
  text: string;
  icon: JSX.Element;
}

const ProfileNavigationLink = ({ to, text, icon }: IProfileNavigationLinkProps) => {
  return (
    <Flex alignItems="center" px="1rem">
      {icon}
      <Box ml="0.5rem">
        <RouterLink to={to}>{text}</RouterLink>
      </Box>
    </Flex>
  );
};

export default ProfileNavigationLink;
