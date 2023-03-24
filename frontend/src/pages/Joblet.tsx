import { Box, Text, Flex, Button } from '@chakra-ui/react';
import { AxiosError } from 'axios';
import { http } from '../helpers/utils';
import { useState, useEffect, useContext } from 'react';
import { UserContext } from '../context/user';
import { IJob, IUserContext } from '../interfaces';
import { MdWork } from 'react-icons/md';
import Header from '../components/Header';
import Job from '../components/Job/Job';

const Joblet = () => {
  const { user } = useContext(UserContext) as IUserContext;
  const [page, setPage] = useState(0);
  const [direction, setDirection] = useState('next');
  const [totalPages, setTotalPages] = useState(0);
  const [jobs, setJobs] = useState<IJob[]>([]);

  useEffect(() => {
    if (user.employerId !== 0) {
      const retrieveEmployerJobs = async () => {
        try {
          const response = await http.get(`/jobs/?page=-1&size=3&direction=next`);
          setPage(response.data.page);
          setTotalPages(response.data.totalPages);
          setJobs(response.data.jobs);
        } catch (err: unknown | AxiosError) {
          if (err instanceof AxiosError && err.response) {
            console.log(err.response);
          }
        }
      };
      retrieveEmployerJobs();
    }
  }, [user.employerId]);

  const paginateEmployerJobs = async (dir: string) => {
    try {
      if (page === totalPages && dir !== 'prev') {
        return;
      }
      setDirection(dir);
      const response = await http.get(`/jobs/?&page=${page}&size=3&direction=${dir}`);
      setPage(response.data.page);
      setTotalPages(response.data.totalPages);
      setJobs(response.data.jobs);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        console.log(err.response);
      }
    }
  };

  return (
    <Box>
      <Box mt="5rem">
        <Header icon={<MdWork />} title="Joblet" text="Explore the job feed" />
      </Box>
      <Box width={['95%', '95%', '590px']} mx="auto">
        <Flex alignItems="center" flexDir="column">
          {jobs.map((job) => {
            return <Job key={job.id} job={job} />;
          })}
        </Flex>
        {jobs.length > 0 && (
          <Flex justifyContent="center" mt="3rem" alignItems="center">
            {page + 1 > 1 && (
              <Button onClick={() => paginateEmployerJobs('prev')}>Prev</Button>
            )}
            <Box>
              <Text mx="1rem">
                {page + 1} of {totalPages}
              </Text>
            </Box>
            {page + 1 !== totalPages && (
              <Button onClick={() => paginateEmployerJobs('next')}>Next</Button>
            )}
          </Flex>
        )}
      </Box>
    </Box>
  );
};

export default Joblet;
