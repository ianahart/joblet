import { Flex } from '@chakra-ui/react';

interface IInputContainerProps {
  children: JSX.Element;
  location?: boolean;
}

const InputContainer = ({ location, children }: IInputContainerProps) => {
  return (
    <Flex
      flexDir={location ? 'column' : 'row'}
      bg="#fff"
      alignItems="center"
      boxShadow="rgba(149, 157, 165, 0.2) 0px 8px 24px"
      borderRadius="8px"
      p="1.5rem"
      justifyContent="center"
      width="100%"
      my="1.5rem"
      mx="auto"
    >
      {children}
    </Flex>
  );
};

export default InputContainer;
