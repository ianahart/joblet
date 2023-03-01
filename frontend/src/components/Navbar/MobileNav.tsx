import { Box, Flex, Spacer } from '@chakra-ui/react';
import { useRef, useCallback, useEffect, RefObject } from 'react';
import NavigationLink from './NavigationLink';
import { AiOutlineClose } from 'react-icons/ai';

interface IMobileNavProps {
  handleSetMobileNavOpen: (open: boolean) => void;
  triggerRef: RefObject<HTMLDivElement>;
}

const MobileNav = ({ handleSetMobileNavOpen, triggerRef }: IMobileNavProps) => {
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
      zIndex="10"
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
        <NavigationLink
          handleSetMobileNavOpen={handleSetMobileNavOpen}
          theme="dark"
          text="Create Account"
          to="register"
        />
        <Spacer />
        <NavigationLink
          handleSetMobileNavOpen={handleSetMobileNavOpen}
          theme="dark"
          text="Sign in"
          to="login"
        />
      </Flex>
    </Box>
  );
};

export default MobileNav;
