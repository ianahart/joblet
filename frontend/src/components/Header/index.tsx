import { Box, Flex, Heading, Text } from '@chakra-ui/react';

interface IHeaderProps {
  icon: JSX.Element;
  title: string;
  text: string;
}

const Header = ({ icon, title, text }: IHeaderProps) => {
  return (
    <Box textAlign="center" as="header">
      <Flex display="flex" justifyContent="center">
        <Flex
          flexDir="column"
          alignItems="center"
          justifyContent="center"
          width="60px"
          fontSize="3rem"
          height="60px"
          borderRadius="50%"
          bg="green.primary"
          color="green.secondary"
        >
          {icon}
        </Flex>
      </Flex>
      <Heading my="1rem" color="black.primary" as="h1">
        {title}
      </Heading>
      <Text my="1rem" color="text.primary">
        {text}
      </Text>
    </Box>
  );
};

export default Header;
