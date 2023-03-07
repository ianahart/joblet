import { Box, Flex, Spacer, Text } from '@chakra-ui/react';
import { AxiosError } from 'axios';
import { RefObject, useCallback, useContext, useEffect, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { UserContext } from '../../context/user';
import { http } from '../../helpers/utils';
import { IUserContext } from '../../interfaces';
import ProfileNavigationLink from './ProfileNavigationLink';
import { CgFileDocument } from 'react-icons/cg';
import { AiOutlineLogout } from 'react-icons/ai';

interface IProfileNavProps {
  triggerRef: RefObject<HTMLDivElement>;
  setShowProfile: (open: boolean) => void;
}

const ProfileNav = ({ triggerRef, setShowProfile }: IProfileNavProps) => {
  const { logout, user } = useContext(UserContext) as IUserContext;
  const menuRef = useRef<HTMLDivElement>(null);
  const navigate = useNavigate();

  const handleOnLogout = async () => {
    try {
      await http.post('/auth/logout', {});
      logout();
      navigate('/login');
    } catch (err: unknown | AxiosError) {
      return;
    }
  };

  const clickAway = useCallback(
    (e: MouseEvent) => {
      const target = e.target as Element;

      if (menuRef.current !== null && triggerRef.current !== null) {
        if (!menuRef.current.contains(target) && triggerRef.current !== target) {
          setShowProfile(false);
        }
      }
    },

    [triggerRef, setShowProfile]
  );

  useEffect(() => {
    window.addEventListener('click', clickAway);

    return () => window.removeEventListener('click', clickAway);
  }, [clickAway]);

  return (
    <Box
      ref={menuRef}
      position="absolute"
      minW="300px"
      right="50px"
      borderRadius="8px"
      bg="#fff"
      top="50px"
      fontSize="1rem"
      color="black.primary"
      minH="400px"
      boxShadow="rgba(0, 0, 0, 0.2) 0px 18px 50px -10px"
    >
      <Box m="1rem">
        <Text fontWeight="bold">{user.email}</Text>
      </Box>
      <ProfileNavigationLink
        to={`profile/${user.id}`}
        text="Profile"
        icon={<CgFileDocument />}
      />

      <Flex alignItems="center" p="1rem">
        <AiOutlineLogout />
        <Text ml="0.5rem" onClick={handleOnLogout} role="button">
          Logout
        </Text>
      </Flex>
    </Box>
  );
};

export default ProfileNav;
