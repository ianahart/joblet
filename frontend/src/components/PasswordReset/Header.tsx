import { AiOutlineCheckCircle, AiOutlineKey } from 'react-icons/ai';
import { Flex, Box, Text, Heading } from '@chakra-ui/react';

interface IPasswordResetHeaderProps {
  text: string;
  heading: string;
  icon: string;
}

const PasswordResetHeader = ({ text, heading, icon }: IPasswordResetHeaderProps) => {
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
          {icon === 'key' ? <AiOutlineKey /> : <AiOutlineCheckCircle />}
        </Flex>
      </Flex>
      <Heading my="1rem" color="black.primary" as="h1">
        {heading}
      </Heading>
      <Text my="1rem" color="text.primary">
        {text}
      </Text>
    </Box>
  );
};

export default PasswordResetHeader;
