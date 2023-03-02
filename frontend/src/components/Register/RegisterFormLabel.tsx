import { Text } from '@chakra-ui/react';

interface IRegisterFormLabelProps {
  text: string;
}

const RegisterFormLabel = ({ text }: IRegisterFormLabelProps) => {
  return (
    <Text
      alignSelf={['auto', 'auto', 'center']}
      width="100px"
      color="black.primary"
      fontWeight="bold"
      mr="1rem"
    >
      {text}
    </Text>
  );
};

export default RegisterFormLabel;
