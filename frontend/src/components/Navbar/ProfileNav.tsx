import { Box, Flex, Spacer, Text } from '@chakra-ui/react';
import { AxiosError } from 'axios';
import { RefObject, useCallback, useContext, useState, useEffect, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { UserContext } from '../../context/user';
import { http } from '../../helpers/utils';
import { IUserContext } from '../../interfaces';
import ProfileNavigationLink from './ProfileNavigationLink';
import { CgFileDocument } from 'react-icons/cg';
import { AiOutlineLogout, AiOutlineHeart } from 'react-icons/ai';
import EmployerLinks from './EmployerLinks';
import { MdWorkOutline } from 'react-icons/md';

interface IProfileNavProps {
  triggerRef: RefObject<HTMLDivElement>;
  setShowProfile: (open: boolean) => void;
}

const ProfileNav = ({ triggerRef, setShowProfile }: IProfileNavProps) => {
  const { logout, user } = useContext(UserContext) as IUserContext;
  const menuRef = useRef<HTMLDivElement>(null);
  const [showEmployer, setShowEmployer] = useState(false);
  const navigate = useNavigate();

  const handleOnLogout = async () => {
    try {
      await http.post('/auth/logout', {});
      setShowProfile(false);
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
      zIndex="10"
      fontSize="1rem"
      color="black.primary"
      minH="400px"
      boxShadow="rgba(0, 0, 0, 0.2) 0px 18px 50px -10px"
    >
      <Box m="1rem">
        <Text fontWeight="bold">{user.email}</Text>
      </Box>
      <ProfileNavigationLink
        to={`profile/${user.profileId}`}
        text="Profile"
        setShowProfile={setShowProfile}
        icon={<CgFileDocument />}
      />
      <ProfileNavigationLink
        to={`/saved-jobs`}
        text="Saved Jobs"
        setShowProfile={setShowProfile}
        icon={<AiOutlineHeart />}
      />

      {user.employerId !== 0 && user.employerId !== null && (
        <>
          <Flex
            onClick={() => setShowEmployer((prevState) => !prevState)}
            p="1rem"
            alignItems="center"
          >
            <Box>
              <MdWorkOutline />
            </Box>
            <Text ml="0.5rem">Employers</Text>
          </Flex>
          {showEmployer && <EmployerLinks setShowProfile={setShowProfile} />}
        </>
      )}

      <Flex
        _hover={{ opacity: 0.8, backgroundColor: '#57cc99' }}
        alignItems="center"
        p="1rem"
      >
        <AiOutlineLogout />
        <Text ml="0.5rem" onClick={handleOnLogout} role="button">
          Logout
        </Text>
      </Flex>
    </Box>
  );
};

export default ProfileNav;
