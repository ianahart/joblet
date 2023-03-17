import { IAvailability, IJobForm } from '../../interfaces';
import { useState } from 'react';
import { Text, Box, Flex, Switch } from '@chakra-ui/react';
import StandardFormInput from '../Form/StandardFormInput';
import InputContainer from '../Form/InputContainer';
import { availabilityState, jobFormState } from '../../data';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';

interface IFormProps {
  employerId: number;
}

const Form = ({ employerId }: IFormProps) => {
  const [availability, setAvailability] = useState(availabilityState);
  const [form, setForm] = useState(jobFormState);

  const updateField = (name: string, value: string | boolean, attribute: string) => {
    setForm((prevState) => ({
      ...prevState,
      [name]: { ...prevState[name as keyof IJobForm], [attribute]: value },
    }));
  };

  const applyErrors = <T,>(errors: T) => {
    for (let prop in errors) {
      updateField(prop, errors[prop] as string, 'error');
    }
  };

  const handleOnAvailabilityClick = (
    e: React.MouseEvent<HTMLDivElement>,
    selectedAvailability: IAvailability
  ) => {
    e.stopPropagation();
    updateField('availability', selectedAvailability.name, 'value');
    hideAvailability(selectedAvailability);
  };

  const hideAvailability = (selectedAvailability: IAvailability) => {
    return availability.map((item) => {
      if (selectedAvailability.id === item.id) {
        item.show = false;
      } else {
        item.show = true;
      }
      return item;
    });
  };

  const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>, name: string) => {
    console.log(e.target.checked);
    updateField(name, e.target.checked, 'value');
  };

  return (
    <Box
      minH="100vh"
      bg="light.secondary"
      display="flex"
      justifyContent="center"
      pt="5rem"
    >
      <Box minH="500px" width={['95%', '95%', '550px']}>
        <form>
          <InputContainer>
            <StandardFormInput
              htmlFor="position"
              errorField="Position"
              width="80%"
              error={form.position.error}
              name={form.position.name}
              value={form.position.value}
              type={form.position.type}
              updateField={updateField}
              label="Position"
            />
          </InputContainer>
          <InputContainer>
            <StandardFormInput
              htmlFor="perHour"
              errorField="Per hour"
              width="80%"
              error={form.perHour.error}
              name={form.perHour.name}
              value={form.perHour.value}
              type={'number'}
              updateField={updateField}
              label="Per Hour"
            />
          </InputContainer>
          <InputContainer location={true}>
            <>
              <StandardFormInput
                htmlFor="availability"
                errorField="Availability"
                width="80%"
                error={form.availability.error}
                name={form.availability.name}
                value={form.availability.value}
                type={form.availability.type}
                updateField={updateField}
                label="Availability"
              />
              <Flex justifyContent="space-around" flexWrap="wrap">
                {availability.map((item) => {
                  return (
                    <Box key={item.id}>
                      {item.show && (
                        <Box
                          cursor="pointer"
                          onClick={(e) => handleOnAvailabilityClick(e, item)}
                          border="1px solid"
                          m="0.5rem"
                          p="0.25rem"
                          borderRadius="20px"
                          borderColor="border.primary"
                        >
                          <Text fontSize="0.9rem" color="text.primary">
                            {item.name}
                          </Text>
                        </Box>
                      )}
                    </Box>
                  );
                })}
              </Flex>
            </>
          </InputContainer>
          <InputContainer>
            <>
              <Text color="text.primary" mr="1rem">
                Urgently Hiring
              </Text>
              <Switch
                colorScheme="teal"
                onChange={(e) => handleOnChange(e, 'urgentlyHiring')}
                size="lg"
              />
            </>
          </InputContainer>
          <InputContainer>
            <>
              <Text color="text.primary" mr="1rem">
                Hiring Multiple Candidates
              </Text>
              <Switch
                colorScheme="teal"
                onChange={(e) => handleOnChange(e, 'multipleCandidates')}
                size="lg"
              />
            </>
          </InputContainer>
        </form>
      </Box>
    </Box>
  );
};

export default Form;
