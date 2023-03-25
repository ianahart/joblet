import { Box, Text, Button, Flex } from '@chakra-ui/react';
import { AxiosError } from 'axios';
import { useContext, useEffect, useState } from 'react';
import { MdWork } from 'react-icons/md';
import { UserContext } from '../context/user';
import { http } from '../helpers/utils';
import { IEmployerJob, IGetEmployerJobResponse, IUserContext } from '../interfaces';
import Header from '../components/Header';
import Job from '../components/Job/Job';

const EmployerJobs = () => {
  const { user } = useContext(UserContext) as IUserContext;
  const [page, setPage] = useState(0);
  const [direction, setDirection] = useState('next');
  const [totalPages, setTotalPages] = useState(0);
  const [jobs, setJobs] = useState<IEmployerJob[]>([]);

  useEffect(() => {
    if (user.employerId !== 0) {
      const retrieveEmployerJobs = async () => {
        try {
          const response = await http.get<IGetEmployerJobResponse>(
            `/jobs/owner?employerId=${user.employerId}&page=-1&size=3&direction=next`
          );
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
      const response = await http.get<IGetEmployerJobResponse>(
        `/jobs/owner?employerId=${user.employerId}&page=${page}&size=3&direction=${dir}`
      );
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
        <Header icon={<MdWork />} title="Employer Jobs" text="Jobs you have created" />
      </Box>
      <Box width={['95%', '95%', '590px']} mx="auto">
        <Flex alignItems="center" flexDir="column">
          {jobs.map((job) => {
            return (
              <Job
                detailsType="employer"
                key={job.id}
                job={job}
                link={`/employer-jobs/${job.id}`}
              />
            );
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

export default EmployerJobs;
