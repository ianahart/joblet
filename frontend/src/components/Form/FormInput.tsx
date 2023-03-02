import { Box, FormControl, FormLabel, Input, Text } from '@chakra-ui/react';
import { AiOutlineEye, AiOutlineEyeInvisible } from 'react-icons/ai';

interface IFormInputProps {
  updateField: (name: string, value: string, attribute: string) => void;
  value: string;
  error: string;
  htmlFor: string;
  name: string;
  errorField: string;
  type: string;
  label: string;
  width: string;
}
const FormInput = ({
  updateField,
  value,
  error,
  htmlFor,
  name,
  errorField,
  type,
  label,
  width,
}: IFormInputProps) => {
  const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value, name } = e.target;
    updateField(name, value, 'value');
  };

  const handleOnClick = () => {
    console.log(type);
    type === 'password'
      ? updateField('password', 'text', 'type')
      : updateField('password', 'password', 'type');
  };

  const handleOnBlur = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value, name } = e.target;
    if (!validateField(value)) {
      const error = `${errorField} must be between 1 and 100 characters.`;
      updateField(name, error, 'error');
    }
  };

  const validateField = (value: string) => {
    if (value.trim().length === 0 || value.length > 100) {
      return false;
    }
    return true;
  };

  const handleOnFocus = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name } = e.target;
    updateField(name, '', 'error');
  };

  return (
    <FormControl my="1.5rem" display="flex" mx="1rem" flexDir="column">
      <Box position="relative">
        <Input
          color="text.primary"
          onChange={handleOnChange}
          onBlur={handleOnBlur}
          onFocus={handleOnFocus}
          width={width}
          name={name}
          id={name}
          type={type}
          value={value}
        />
      </Box>
      {name === 'password' && (
        <Box
          zIndex="5"
          onClick={handleOnClick}
          cursor="pointer"
          position="absolute"
          color="text.primary"
          top="10px"
          fontSize="1.5rem"
          right="40px"
        >
          {type === 'password' ? <AiOutlineEye /> : <AiOutlineEyeInvisible />}
        </Box>
      )}
      <FormLabel color="text.primary" width={width} htmlFor={htmlFor}>
        {label}
      </FormLabel>
      {error && (
        <Text color="error.primary" fontSize="0.85rem">
          {error}
        </Text>
      )}
    </FormControl>
  );
};

export default FormInput;
