import { Flex, Box } from '@chakra-ui/react';
import { Link as RouterLink } from 'react-router-dom';

interface IProfileNavigationLinkProps {
  to: string;
  text: string;
  icon: JSX.Element;
  setShowProfile: (show: boolean) => void;
}

const ProfileNavigationLink = ({
  to,
  text,
  icon,
  setShowProfile,
}: IProfileNavigationLinkProps) => {
  const handleOnClick = (e: React.MouseEvent<HTMLDivElement>) => {
    e.stopPropagation();
    setShowProfile(false);
  };

  return (
    <Flex
      onClick={handleOnClick}
      _hover={{ opacity: 0.8, backgroundColor: '#57cc99' }}
      alignItems="center"
      px="1rem"
      py="1rem"
    >
      {icon}
      <Box ml="0.5rem">
        <RouterLink to={to}>{text}</RouterLink>
      </Box>
    </Flex>
  );
};

export default ProfileNavigationLink;
