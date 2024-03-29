import { Box, Heading, Text, Flex } from '@chakra-ui/react';
import { IJob, ISavedJob } from '../../interfaces';
import { AiOutlineCheck, AiOutlineClockCircle } from 'react-icons/ai';
import { HiInformationCircle, HiUserGroup } from 'react-icons/hi';
import { Link as RouterLink } from 'react-router-dom';
import SaveJobActions from '../Job/SaveJobActions';

interface IJobProps {
  job: IJob | ISavedJob;
  link: string;
  detailsType: string;
}

const Job = ({ job, link, detailsType }: IJobProps) => {
  return (
    <Box
      width="100%"
      p="0.5rem"
      border="1px solid"
      borderColor="border.primary"
      borderRadius="8px"
      my="1.5rem"
    >
      {/* {detailsType === 'user' && <SaveJobActions jobId={job.id} />}*/}
      <Heading my="1rem" fontSize="1.1rem">
        {job.position}
      </Heading>
      <Text color="text.primary">{job.companyName}</Text>
      <Text color="text.primary">{job.location}</Text>
      <Flex alignItems="flex-start" flexDir="column">
        <Flex
          flexDir={['column', 'column', 'row']}
          p="0.25rem"
          fontSize="0.9rem"
          my="1rem"
          alignItems={['flex-start', 'flex-start', 'center']}
        >
          <Flex
            color="green"
            alignItems="center"
            p="0.25rem"
            borderRadius="8px"
            bg="rgba(94,218,94, 0.4)"
            wordBreak="break-all"
          >
            <AiOutlineClockCircle />
            <Text fontSize="0.9rem" fontWeight="bold" mx="0.5rem">
              {job.availability}
            </Text>
            <AiOutlineCheck />
          </Flex>
          {job.multipleCandidates && (
            <Flex mx="0.5rem" alignItems="center">
              <HiUserGroup />
              <Text mx="0.5rem">Hiring multiple candidates</Text>
            </Flex>
          )}
        </Flex>
        {job.urgentlyHiring && (
          <Box ml="0.5rem" mb="1rem">
            <Text fontSize="0.85rem" color="text.primary">
              Urgently Hiring
            </Text>
          </Box>
        )}
      </Flex>
      <Flex fontSize="0.85rem" alignItems="center">
        <HiInformationCircle />

        <RouterLink to={link}>View Details</RouterLink>
      </Flex>
    </Box>
  );
};

export default Job;
