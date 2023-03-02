import { Box, Heading } from '@chakra-ui/react';

interface IFormContainerProps {
  children: React.ReactNode;
  width: string[];
  minH: string;
  title: string;
}

const FormContainer = ({ title, width, minH, children }: IFormContainerProps) => {
  return (
    <Box
      width={width}
      minH={minH}
      borderRadius="8px"
      textAlign="center"
      boxShadow="rgba(0, 0, 0, 0.2) 0px 18px 50px -10px"
      margin="0 auto"
      background="#fff"
    >
      <Box as="header">
        <Heading
          borderTopLeftRadius="8px"
          borderTopRightRadius="8px"
          color="light.primary"
          p="0.5rem"
          bg="green.primary"
          fontSize="1.5rem"
        >
          {title}
        </Heading>
      </Box>
      {children}
    </Box>
  );
};

export default FormContainer;
