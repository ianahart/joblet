import { Box, Flex, Spacer, Text } from '@chakra-ui/react';
import { useRef, useCallback, useEffect, RefObject, useContext } from 'react';
import NavigationLink from './NavigationLink';
import { AiOutlineClose } from 'react-icons/ai';
import { AxiosError } from 'axios';
import { http } from '../../helpers/utils';
import { useNavigate } from 'react-router-dom';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';

interface IMobileNavProps {
  handleSetMobileNavOpen: (open: boolean) => void;
  triggerRef: RefObject<HTMLDivElement>;
}

const MobileNav = ({ handleSetMobileNavOpen, triggerRef }: IMobileNavProps) => {
  const navigate = useNavigate();
  const { logout, user } = useContext(UserContext) as IUserContext;

  const handleOnLogout = async () => {
    try {
      await http.post('/auth/logout', {});
      logout();
      handleSetMobileNavOpen(false);
      navigate('/login');
    } catch (err: unknown | AxiosError) {
      return;
    }
  };

  const menuRef = useRef<HTMLDivElement>(null);

  const handleClose = (e: React.MouseEvent<HTMLDivElement>) => {
    e.stopPropagation();
    handleSetMobileNavOpen(false);
  };

  const clickAway = useCallback(
    (e: MouseEvent) => {
      const target = e.target as Element;

      if (menuRef.current !== null && triggerRef.current !== null) {
        if (!menuRef.current.contains(target) && triggerRef.current !== target) {
          handleSetMobileNavOpen(false);
        }
      }
    },
    [handleSetMobileNavOpen, triggerRef]
  );

  useEffect(() => {
    window.addEventListener('click', clickAway);
    return () => window.removeEventListener('click', clickAway);
  }, [clickAway]);

  return (
    <Box
      className="mobile-nav-forward"
      ref={menuRef}
      position="absolute"
      top="-20px"
      right="0"
      background="blue"
      zIndex="99"
      minWidth="250px"
      bg="#fff"
      boxShadow="rgba(0, 0, 0, 0.2) 0px 18px 50px -10px;"
      minH="100vh"
      as="nav"
    >
      <Flex onClick={handleClose} padding="0.5rem" justifyContent="flex-end">
        <AiOutlineClose fontSize="1.5rem" />
      </Flex>
      <Flex flexDir="column" height="100%">
        <NavigationLink
          handleSetMobileNavOpen={handleSetMobileNavOpen}
          theme="dark"
          text="Company Reviews"
          to="company-reviews"
        />
        <Spacer />
        {user.id === 0 && (
          <NavigationLink
            handleSetMobileNavOpen={handleSetMobileNavOpen}
            theme="dark"
            text="Create Account"
            to="register"
          />
        )}
        <Spacer />
        {user.id === 0 && (
          <NavigationLink
            handleSetMobileNavOpen={handleSetMobileNavOpen}
            theme="dark"
            text="Sign in"
            to="login"
          />
        )}
        {user.id !== 0 && (
          <NavigationLink
            handleSetMobileNavOpen={handleSetMobileNavOpen}
            theme="dark"
            text="Profile"
            to={`profile/${user.id}`}
          />
        )}
        {user.id !== 0 && (
          <NavigationLink
            handleSetMobileNavOpen={handleSetMobileNavOpen}
            theme="dark"
            text="Employers"
            to={`create-employer`}
          />
        )}
        {user.id !== 0 && user.employerId !== null && (
          <NavigationLink
            handleSetMobileNavOpen={handleSetMobileNavOpen}
            theme="dark"
            text="Employer Jobs"
            to={`employer-jobs`}
          />
        )}
        {user.id !== 0 && (
          <Text
            onClick={handleOnLogout}
            p="0.5rem"
            role="button"
            color="black.primary"
            fontWeight="bold"
          >
            Logout
          </Text>
        )}
      </Flex>
    </Box>
  );
};

export default MobileNav;
