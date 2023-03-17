import { Box, Flex, Text } from '@chakra-ui/react';
import { nanoid } from 'nanoid';
import { useLocation } from 'react-router-dom';
import Form from '../components/Job/Form';

const CreateJob = () => {
  const location = useLocation();
  return (
    <Box
      minH="100vh"
      bg="light.secondary"
      display="flex"
      justifyContent="center"
      pt="5rem"
    >
      <Form employerId={location.state.id} />
    </Box>
  );
};

export default CreateJob;
