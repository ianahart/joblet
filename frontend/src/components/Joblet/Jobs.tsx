import { Box, Input, Text, Flex, Button } from '@chakra-ui/react';
import { useState } from 'react';
import { IJob } from '../../interfaces';
import Job from '../Job/Job';

interface IJobsProps {
  totalPages: number;
  page: number;
  jobs: IJob[];
  paginateJobs: (dir: string) => void;
  search: (searchValue: string) => void;
  resetJobs: () => void;
}

const Jobs = ({
  totalPages,
  page,
  jobs,
  paginateJobs,
  search,
  resetJobs,
}: IJobsProps) => {
  const [inputValue, setInputValue] = useState('');
  const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(e.target.value);
  };

  const handleOnKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key.toUpperCase() === 'BACKSPACE' && inputValue.length - 1 === 0) {
      resetJobs();
    }
  };

  return (
    <Box width={['95%', '95%', '590px']} mx="auto">
      <Box display="flex">
        <Input
          onKeyDown={handleOnKeyDown}
          onChange={handleOnChange}
          placeholder="Search job positions"
        />
        <Button mx="1rem" colorScheme="teal" onClick={() => search(inputValue)}>
          Search
        </Button>
      </Box>
      <Flex alignItems="center" flexDir="column">
        {jobs.map((job) => {
          return (
            <Job detailsType="user" key={job.id} job={job} link={`/jobs/${job.id}`} />
          );
        })}
      </Flex>
      {jobs.length > 0 && (
        <Flex justifyContent="center" mt="3rem" alignItems="center">
          {page + 1 > 1 && <Button onClick={() => paginateJobs('prev')}>Prev</Button>}
          <Box>
            <Text mx="1rem">
              {page + 1} of {totalPages}
            </Text>
          </Box>
          {page + 1 !== totalPages && (
            <Button onClick={() => paginateJobs('next')}>Next</Button>
          )}
        </Flex>
      )}
    </Box>
  );
};

export default Jobs;
