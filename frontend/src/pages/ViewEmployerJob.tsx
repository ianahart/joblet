import { Box } from '@chakra-ui/react';
import { useState } from 'react';
import { AxiosError } from 'axios';
import { useParams } from 'react-router-dom';
import { http } from '../helpers/utils';
import { useEffectOnce } from '../hooks/UseEffectOnce';
import { employerJobState } from '../data';
import { IEmployerJob } from '../interfaces';
import JobDetails from '../components/Job/JobDetails';
const ViewEmployerJob = () => {
  const params = useParams();
  const [job, setJob] = useState<IEmployerJob>(employerJobState);

  const retrieveEmployerJob = async (id: string) => {
    try {
      const response = await http.get<IEmployerJob>(`/jobs/owner/${id}`);
      setJob(response.data);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
      }
    }
  };

  useEffectOnce(() => {
    if (params.id) {
      retrieveEmployerJob(params.id);
    }
  });

  return (
    <Box display="flex" justifyContent="center" mt="5rem" minH="100vh">
      <JobDetails job={job} detailsType="employer" />
    </Box>
  );
};

export default ViewEmployerJob;
