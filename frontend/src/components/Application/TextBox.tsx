import { Box, Text } from '@chakra-ui/react';

interface ITextBoxProps {
  label: string;
  value: string;
  width: string;
}

const TextBox = ({ label, value, width }: ITextBoxProps) => {
  return (
    <Box width={width} color="text.primary" my="1.5rem">
      <Text>{label}</Text>
      <Text
        border="1px solid"
        borderRadius="8px"
        p="0.5rem 1rem"
        borderColor="border.primary"
      >
        {value}
      </Text>
    </Box>
  );
};

export default TextBox;
