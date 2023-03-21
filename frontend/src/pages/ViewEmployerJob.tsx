import { Box } from '@chakra-ui/react';
import { useState } from 'react';
import { AxiosError } from 'axios';
import { useParams } from 'react-router-dom';
import { http } from '../helpers/utils';
import { useEffectOnce } from '../hooks/UseEffectOnce';
const ViewEmployerJob = () => {
  const params = useParams();

  const retrieveEmployerJob = async (id: string) => {
    try {
      const response = await http.get(`/jobs/owner/${id}`);
      console.log(response);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        console.log(err.response);
      }
    }
  };

  useEffectOnce(() => {
    if (params.id) {
      retrieveEmployerJob(params.id);
    }
  });

  return <Box>View Employer Job</Box>;
};

export default ViewEmployerJob;
