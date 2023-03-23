import { IAvailability, IEmployerJobMin, IJobForm, IUserContext } from '../../interfaces';
import { useContext, useEffect, useState } from 'react';
import { Text, Box, Flex, Switch, Heading, Image, Button } from '@chakra-ui/react';
import StandardFormInput from '../Form/StandardFormInput';
import createJobImg from '../../images/create-job.jpg';
import InputContainer from '../Form/InputContainer';
import { availabilityState, jobFormState } from '../../data';
import ReactQuill from 'react-quill';
import { AiOutlineFullscreen, AiOutlineFullscreenExit } from 'react-icons/ai';
import 'react-quill/dist/quill.snow.css';
import { Axios, AxiosError } from 'axios';
import { http } from '../../helpers/utils';
import { useNavigate } from 'react-router-dom';
import { UserContext } from '../../context/user';

interface IFormProps {
  employerId: number;
  type: string;
  title: string;
  btnText: string;
  endpoint: string;
  jobId: string | undefined;
}

const Form = ({ employerId, type, title, btnText, endpoint, jobId }: IFormProps) => {
  const navigate = useNavigate();
  const { user } = useContext(UserContext) as IUserContext;
  const [editorValue, setEditorValue] = useState('');
  const [availability, setAvailability] = useState(availabilityState);
  const [editorFullScreen, setEditorFullScreen] = useState(false);
  const [form, setForm] = useState(jobFormState);

  useEffect(() => {
    if (jobId !== undefined) {
      const syncForm = async () => {
        try {
          const response = await http.get<IEmployerJobMin>(`jobs/owner/${jobId}/sync`);
          console.log(response);
          setEditorValue(response.data.body);
          const { data } = response;
          for (let prop in data) {
            console.log(prop);
            //@ts-ignore
            updateField(prop, data[prop as keyof IJobForm], 'value');
          }
          console.log(form);
        } catch (err: unknown | AxiosError) {
          if (err instanceof AxiosError && err.response) {
            console.log(err);
            return;
          }
        }
      };
      syncForm();
    }
  }, [jobId]);

  const updateField = (name: string, value: string | boolean, attribute: string) => {
    setForm((prevState) => ({
      ...prevState,
      [name]: { ...prevState[name as keyof IJobForm], [attribute]: value },
    }));
  };

  const applyErrors = <T,>(errors: T) => {
    for (let prop in errors) {
      updateField(prop, errors[prop] as any, 'error');
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
    updateField(name, e.target.checked, 'value');
  };

  const checkForErrors = () => {
    let errors = false;
    for (const [_, field] of Object.entries(form)) {
      const { value, error } = field;
      if (error.length) {
        errors = true;
      }
    }
    return errors;
  };

  const handleOnSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (checkForErrors()) return;
    if (type === 'create') {
      createJob(endpoint);
    } else {
      updateJob(endpoint);
    }
  };

  const createJob = (endpoint: string) => {
    try {
      const response = http.post(endpoint, {
        position: form.position.value,
        perHour: form.perHour.value,
        employerId,
        availability: form.availability.value,
        urgentlyHiring: form.urgentlyHiring.value,
        multipleCandidates: form.multipleCandidates.value,
        body: editorValue,
      });
      navigate('/joblet');
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        applyErrors(err.response.data);
      }
    }
  };

  const updateJob = (endpoint: string) => {};

  return (
    <Box
      minH="100vh"
      bg={editorFullScreen ? 'rgba(0, 0, 0, 0.7)' : '#f2efef'}
      display="flex"
      width="100%"
      justifyContent="center"
      pt="5rem"
    >
      <Box minH="500px" width={['95%', '95%', '550px']}>
        <form onSubmit={handleOnSubmit}>
          <InputContainer>
            <Box>
              <Heading color="text.primary" as="h1">
                {title}
              </Heading>
              <Image src={createJobImg} alt="person working on a computer" />
            </Box>
          </InputContainer>
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
                isChecked={form.urgentlyHiring.value ? true : false}
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
                isChecked={form.multipleCandidates.value ? true : false}
                onChange={(e) => handleOnChange(e, 'multipleCandidates')}
                size="lg"
              />
            </>
          </InputContainer>
          <Box
            className={editorFullScreen ? 'expand-editor' : ''}
            bg="#fff"
            alignItems="center"
            boxShadow="rgba(149, 157, 165, 0.2) 0px 8px 24px"
            borderRadius="8px"
            p="1.5rem"
            justifyContent="center"
            my="1.5rem"
            mx="auto"
          >
            <Flex justifyContent="flex-end">
              {editorFullScreen ? (
                <Box
                  onClick={() => setEditorFullScreen(false)}
                  fontSize="2rem"
                  cursor="pointer"
                  color="green.primary"
                >
                  <AiOutlineFullscreenExit />
                </Box>
              ) : (
                <Box
                  cursor="pointer"
                  onClick={() => setEditorFullScreen(true)}
                  fontSize="2rem"
                  color="green.primary"
                >
                  <AiOutlineFullscreen />
                </Box>
              )}
            </Flex>
            <Text color="text.primary">Description of Job</Text>
            <ReactQuill theme="snow" value={editorValue} onChange={setEditorValue} />
          </Box>
          <InputContainer>
            <Button type="submit" colorScheme="teal" width="90%">
              {btnText}
            </Button>
          </InputContainer>
        </form>
      </Box>
    </Box>
  );
};

export default Form;
