import { Box, Text, Flex, Button } from '@chakra-ui/react';
import { AxiosError } from 'axios';
import { useState } from 'react';
import { FaNewspaper } from 'react-icons/fa';
import { useLocation, useNavigate } from 'react-router-dom';
import TextBox from '../components/Application/TextBox';
import Header from '../components/Header';
import { applicationState } from '../data';
import { http } from '../helpers/utils';
import { useEffectOnce } from '../hooks/UseEffectOnce';
import { IRetrieveApplicationResponse } from '../interfaces';

const EmployerApplication = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { jobId, id, profileId, userId } = location.state.application;
  const [application, setApplication] = useState(applicationState);

  const retrieveApplication = async () => {
    try {
      const response = await http.get<IRetrieveApplicationResponse>(
        `applications/${id}?jobId=${jobId}&userId=${userId}&profileId=${profileId}`
      );
      setApplication(response.data.applicationDetailsDto);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        console.log(err.response);
      }
    }
  };

  useEffectOnce(() => {
    retrieveApplication();
  });

  const viewApplication = (e: React.MouseEvent<HTMLDivElement>) => {
    navigate('/document-view/', { state: { resume: application.resume } });
  };

  const deleteApplication = async (e: React.MouseEvent<HTMLButtonElement>) => {
    try {
      e.stopPropagation();
      const response = await http.delete(`/applications/${id}`);
      navigate('/employers/inbox');
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        console.log(err.response);
      }
    }
  };

  return (
    <Box minH="100vh">
      <Box mt="5rem">
        <Header
          title="Job Application"
          text={`Job application for ${location.state.application.jobPosition}`}
          icon={<FaNewspaper />}
        />
        <Box width={['95%', '95%', '650px']} mt="4rem" mx="auto">
          <Flex justifyContent="space-evenly">
            <TextBox label="First Name" value={application.firstName} width="45%" />
            <TextBox label="Last Name" value={application.lastName} width="45%"></TextBox>
          </Flex>
          <Flex justifyContent="space-evenly">
            <TextBox label="Email" value={application.email} width="90%" />
          </Flex>
          <Flex justifyContent="space-evenly">
            <TextBox label="City" value={application.city} width="30%" />
            <TextBox label="State" value={application.state} width="30%" />
            <TextBox label="Country" value={application.country} width="30%" />
          </Flex>
          <Flex justifyContent="space-evenly">
            <TextBox label="Phone Number" value={application.phoneNumber} width="90%" />
          </Flex>
          <Flex
            my="1rem"
            justifyContent="space-evenly"
            cursor="pointer"
            onClick={viewApplication}
          >
            <Button onClick={(e) => deleteApplication(e)} colorScheme="red" width="90%">
              Delete
            </Button>
          </Flex>
          <Flex
            my="1rem"
            justifyContent="space-evenly"
            cursor="pointer"
            onClick={(e) => viewApplication(e)}
          >
            <Button colorScheme="teal" width="90%">
              View Application
            </Button>
          </Flex>
        </Box>
      </Box>
    </Box>
  );
};

export default EmployerApplication;
