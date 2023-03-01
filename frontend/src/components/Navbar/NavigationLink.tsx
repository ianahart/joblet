import { Box, ListItem, Text } from '@chakra-ui/react';
import { Link as RouterLink } from 'react-router-dom';
import { BsChevronRight } from 'react-icons/bs';

interface INavigationLinkProps {
  text: string;
  to: string;
  theme: string;
  handleSetMobileNavOpen?: (open: boolean) => void;
}

const NavigationLink = ({
  text,
  to,
  theme,
  handleSetMobileNavOpen,
}: INavigationLinkProps) => {
  const handleClick = (e: React.MouseEvent<HTMLDivElement>) => {
    e.stopPropagation();
    handleSetMobileNavOpen && handleSetMobileNavOpen(false);
  };
  return (
    <Box onClick={(e) => handleClick(e)}>
      <RouterLink to={to}>
        <ListItem
          _hover={{ opacity: 0.75 }}
          display="flex"
          alignItems="center"
          justifyContent="space-between"
          padding="0.5rem"
          borderBottom={theme === 'dark' ? '1px solid' : ''}
          borderBottomColor={theme === 'dark' ? 'light.primary' : ''}
          fontWeight="bold"
          my={theme === 'dark' ? '0.8rem' : '0rem'}
          color={theme === 'light' ? 'light.primary' : 'text.tertiary'}
          mx={theme === 'dark' ? '0rem' : '1rem'}
        >
          <Text>{text}</Text>
          {theme === 'dark' ? <BsChevronRight /> : ''}
        </ListItem>
      </RouterLink>
    </Box>
  );
};

export default NavigationLink;
