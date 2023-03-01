import { Box, Image, Spacer, UnorderedList } from '@chakra-ui/react';
import { useRef, useCallback, useEffect, useState } from 'react';
import { Link as RouterLink } from 'react-router-dom';
import jobletLogo from '../../images/joblet.png';
import { AiOutlineMenu } from 'react-icons/ai';
import MobileNav from './MobileNav';
import DesktopNav from './DesktopNav';

const Navbar = () => {
  const triggerRef = useRef<HTMLDivElement>(null);

  const [isMobileNavOpen, setIsMobileNavOpen] = useState(false);

  const handleSetMobileNavOpen = useCallback((isOpen: boolean) => {
    setIsMobileNavOpen(isOpen);
  }, []);

  const handleResize = useCallback(
    (event: Event) => {
      const target = event.target as Window;
      if (target.innerWidth > 768) {
        handleSetMobileNavOpen(false);
      }
    },
    [handleSetMobileNavOpen]
  );

  useEffect(() => {
    window.addEventListener('resize', handleResize);

    return () => window.removeEventListener('resize', handleResize);
  }, [handleResize]);

  return (
    <Box as="nav" bg="blue.primary" py="0.5rem">
      <UnorderedList listStyleType="none" display="flex" alignItems="center">
        <RouterLink to="/">
          <Image width="50px" height="50px" borderRadius="8px" src={jobletLogo} />
        </RouterLink>
        <Spacer />
        <Box
          ref={triggerRef}
          position="relative"
          onClick={() => setIsMobileNavOpen(true)}
          cursor="pointer"
          p="0.5rem"
          display={['block', 'block', 'none']}
        >
          <AiOutlineMenu pointerEvents="none" color="e4e3e3" fontSize="1.5rem" />
          {isMobileNavOpen && (
            <MobileNav
              triggerRef={triggerRef}
              handleSetMobileNavOpen={handleSetMobileNavOpen}
            />
          )}
        </Box>
        {!isMobileNavOpen && <DesktopNav />}
      </UnorderedList>
    </Box>
  );
};

export default Navbar;
