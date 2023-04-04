import { Box, Text, Flex, Button, Input } from '@chakra-ui/react';
import { AxiosError } from 'axios';
import { http } from '../helpers/utils';
import { debounce } from 'lodash';
import { useCallback, useState, useEffect, useContext } from 'react';
import { UserContext } from '../context/user';
import { IGetSearchesResponse, IJob, ISearch, IUserContext } from '../interfaces';
import { MdWork } from 'react-icons/md';
import Header from '../components/Header';
import Job from '../components/Job/Job';
import Jobs from '../components/Joblet/Jobs';
import Searches from '../components/Joblet/Searches';

const Joblet = () => {
  const { user } = useContext(UserContext) as IUserContext;
  const [page, setPage] = useState(0);
  const [direction, setDirection] = useState('next');
  const [searches, setSearches] = useState<ISearch[]>([]);
  const [active, setActive] = useState('jobs');
  const [totalPages, setTotalPages] = useState(0);
  const [jobs, setJobs] = useState<IJob[]>([]);

  useEffect(() => {
    if (user.employerId !== 0) {
      const retrieveJobs = async () => {
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
      retrieveJobs();
    }
  }, [user.employerId]);

  const paginateJobs = async (dir: string) => {
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
        return;
      }
    }
  };

  const search = async (value: string) => {
    try {
      const response = await http.get(
        `/jobs/search?page=-1&size=2&direction=next&q=${value}`
      );
      setPage(response.data.page);
      setTotalPages(response.data.totalPages);
      setJobs(response.data.jobs);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        return;
      }
    }
  };

  const resetJobs = async () => {
    try {
      setPage(0);
      setTotalPages(0);
      setJobs([]);
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

  const fetchSearches = async () => {
    try {
      setActive('searches');
      const response = await http.get<IGetSearchesResponse>(`/searches/`);
      setSearches(response.data.searches);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        console.log(err.response);
      }
    }
  };

  const research = async (value: string) => {
    await search(value);
    setActive('jobs');
  };

  const removeSearch = async (id: number) => {
    try {
      setSearches((prevState) => prevState.filter((search) => search.id !== id));
      await http.delete(`/searches/${id}`);
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
      <Flex my="2rem" justifyContent="center">
        <Box onClick={() => setActive('jobs')} mx="1rem" cursor="pointer">
          <Text fontWeight="bold">Job Feed</Text>
          <Box className={active === 'jobs' ? 'slide-right-div' : ''}></Box>
        </Box>
        <Box onClick={fetchSearches} mx="1rem" cursor="pointer">
          <Text fontWeight="bold">Recent Searches</Text>
          <Box className={active === 'searches' ? 'slide-left-div' : ''}></Box>
        </Box>
      </Flex>
      {active === 'jobs' && (
        <Jobs
          resetJobs={resetJobs}
          search={search}
          page={page}
          totalPages={totalPages}
          jobs={jobs}
          paginateJobs={paginateJobs}
        />
      )}
      {active === 'searches' && (
        <Searches research={research} searches={searches} removeSearch={removeSearch} />
      )}
    </Box>
  );
};

export default Joblet;
