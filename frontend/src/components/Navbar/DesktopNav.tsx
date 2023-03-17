import { Box, Flex, Text } from '@chakra-ui/react';
import { useContext, useRef, useState } from 'react';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';
import NavigationLink from './NavigationLink';
import { FaRegUser } from 'react-icons/fa';
import ProfileNav from './ProfileNav';

const DesktopNav = () => {
  const { user } = useContext(UserContext) as IUserContext;
  const [showProfile, setShowProfile] = useState(false);
  const triggerRef = useRef<HTMLDivElement>(null);

  const handleOnShowProfile = (e: React.MouseEvent<HTMLDivElement>, show: boolean) => {
    e.stopPropagation();
    setShowProfile(show);
  };

  return (
    <Flex display={['none', 'none', 'flex']} alignItems="center">
      <NavigationLink theme="light" text="Company Reviews" to="company-reviews" />
      {user.id === 0 && (
        <NavigationLink theme="light" text="Create Account" to="register" />
      )}
      {user.id !== 0 && (
        <Box
          mx="1rem"
          p="0.5rem"
          onClick={(e) => handleOnShowProfile(e, true)}
          color="light.primary"
          fontSize="1.5rem"
          cursor="pointer"
        >
          <Box ref={triggerRef}>
            <FaRegUser />
          </Box>
          {showProfile && (
            <ProfileNav setShowProfile={setShowProfile} triggerRef={triggerRef} />
          )}
        </Box>
      )}

      {user.id === 0 && <NavigationLink theme="light" text="Sign in" to="login" />}
      {user.id !== 0 && (
        <Box display="flex" alignItems="center">
          <Box height="30px" width="2px" bg="light.primary"></Box>
          <NavigationLink theme="light" text="Employers/Post Job" to="create-employer" />
        </Box>
      )}
    </Flex>
  );
};

export default DesktopNav;
