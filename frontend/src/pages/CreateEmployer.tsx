import {
  Box,
  Button,
  Flex,
  Heading,
  Image,
  Radio,
  RadioGroup,
  Stack,
} from '@chakra-ui/react';
import {
  ICreateEmployerForm,
  ICreateEmployerResponse,
  IDropdownData,
  IGetEmployerResponse,
  IUserContext,
} from '../interfaces';
import { createEmployerState, locationState, numOfEmployees } from '../data';
import { useContext, useEffect, useState } from 'react';
import createEmployerImg from '../images/create-employer.jpg';
import StandardFormInput from '../components/Form/StandardFormInput';
import InputContainer from '../components/Form/InputContainer';
import FormDropdown from '../components/Form/FormDropdown';
import { AxiosError } from 'axios';
import { http } from '../helpers/utils';
import { useNavigate } from 'react-router-dom';
import { UserContext } from '../context/user';
const CreateEmployer = () => {
  const navigate = useNavigate();
  const { user } = useContext(UserContext) as IUserContext;
  const [radioValue, setRadioValue] = useState('2');
  const [form, setForm] = useState(createEmployerState);
  const [location, setLocation] = useState<IDropdownData>();

  const updateField = (name: string, value: string, attribute: string) => {
    setForm((prevState) => ({
      ...prevState,
      [name]: { ...prevState[name as keyof ICreateEmployerForm], [attribute]: value },
    }));
  };

  const updateLocation = (location: IDropdownData) => {
    setLocation(location);
  };

  const checkForErrors = () => {
    let errors = false;
    for (const [_, field] of Object.entries(form)) {
      const { value, error } = field;
      if (value.trim().length === 0 || error.length) {
        errors = true;
      }
    }
    return errors;
  };

  const handleOnSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (checkForErrors()) return;
    await createEmployer();
  };

  const createEmployer = async () => {
    try {
      const response = await http.post<ICreateEmployerResponse>('/employers/', {
        companyName: form.companyName.value,
        numOfEmployees: form.numOfEmployees.value,
        firstName: form.firstName.value,
        lastName: form.lastName.value,
        email: form.email.value,
        location: form.location.value,
        locationQuestionId: location?.id,
      });

      navigate('/create-job', { state: { id: response.data.id } });
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        applyErrors(err.response.data);
      }
    }
  };

  const applyErrors = <T,>(errors: T) => {
    for (let prop in errors) {
      updateField(prop, errors[prop] as any, 'error');
    }
  };

  const syncPrevEmployerInfo = async () => {
    try {
      const response = await http.get<IGetEmployerResponse>(
        `/employers/${user.employerId}`
      );
      console.log(response);
      for (let prop in response.data) {
        if (prop !== 'locationQuestionId') {
          updateField(
            prop,
            response.data[prop as keyof IGetEmployerResponse] as string,
            'value'
          );
        }
        const prevLocation = locationState.find(
          (item) => item.id === response.data.locationQuestionId
        );
        setLocation(prevLocation);
      }
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        return;
      }
    }
  };

  const handleRadioChange = (nextValue: string) => {
    setRadioValue(nextValue);

    if (nextValue === '1') {
      syncPrevEmployerInfo();
    } else {
      for (const [_, field] of Object.entries(form)) {
        updateField(field.name, '', 'value');
      }
    }
  };

  return (
    <Box
      bg="light.secondary"
      display="flex"
      justifyContent="center"
      pt="5rem"
      minH="100vh"
    >
      <Box minH="500px" width={['95%', '95%', '550px']}>
        <form onSubmit={handleOnSubmit}>
          <Flex
            flexDir={['column', 'column', 'row']}
            bg="#fff"
            boxShadow="rgba(149, 157, 165, 0.2) 0px 8px 24px"
            borderRadius="8px"
            p="1.5rem"
            justifyContent="space-between"
            width="100%"
            my="1.5rem"
            mx="auto"
          >
            <Heading color="text.primary" as="h1">
              Become an Employer
            </Heading>
            <Image width="200px" src={createEmployerImg} />
          </Flex>
          {user.employerId !== null && (
            <InputContainer>
              <RadioGroup onChange={handleRadioChange} value={radioValue}>
                <Stack direction="row">
                  <Radio value="1">Previous Employer Information</Radio>
                  <Radio value="2">Start new</Radio>
                </Stack>
              </RadioGroup>
            </InputContainer>
          )}

          <InputContainer>
            <StandardFormInput
              htmlFor="email"
              errorField="Email"
              width="80%"
              error={form.email.error}
              name={form.email.name}
              value={form.email.value}
              type={form.email.type}
              updateField={updateField}
              label="Email"
            />
          </InputContainer>
          <InputContainer>
            <>
              <StandardFormInput
                htmlFor="firstName"
                errorField="First name"
                width="95%"
                error={form.firstName.error}
                name={form.firstName.name}
                value={form.firstName.value}
                type={form.firstName.type}
                updateField={updateField}
                label="First Name"
              />

              <StandardFormInput
                htmlFor="lastName"
                errorField="Last name"
                width="95%"
                error={form.lastName.error}
                name={form.lastName.name}
                value={form.lastName.value}
                type={form.lastName.type}
                updateField={updateField}
                label="Last Name"
              />
            </>
          </InputContainer>
          <InputContainer>
            <StandardFormInput
              htmlFor="companyName"
              errorField="Company name"
              width="80%"
              error={form.companyName.error}
              name={form.companyName.name}
              value={form.companyName.value}
              type={form.companyName.type}
              updateField={updateField}
              label="Company Name"
            />
          </InputContainer>
          <InputContainer>
            <FormDropdown
              updateField={updateField}
              name={form.numOfEmployees.name}
              label="Number of Employees"
              value={form.numOfEmployees.value}
              data={numOfEmployees}
            />
          </InputContainer>

          <InputContainer location={true}>
            <>
              <FormDropdown
                updateField={updateField}
                updateObject={updateLocation}
                name={form.location.name}
                location={location}
                label="Which option best describes this job's location?"
                data={locationState}
              />
              {location && (
                <StandardFormInput
                  htmlFor="location"
                  errorField="Location"
                  width="80%"
                  error={form.location.error}
                  name={form.location.name}
                  value={form.location.value}
                  type={form.location.type}
                  updateField={updateField}
                  label={location?.question ? location.question : ''}
                />
              )}
            </>
          </InputContainer>
          <InputContainer>
            <Flex justifyContent="center" width="100%">
              <Button width="80%" colorScheme="teal" type="submit">
                Save & Continue
              </Button>
            </Flex>
          </InputContainer>
        </form>
      </Box>
    </Box>
  );
};

export default CreateEmployer;
