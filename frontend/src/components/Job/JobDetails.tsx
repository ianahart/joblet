import { Box, Button, Flex, Heading, Text } from '@chakra-ui/react';
import { IEmployerJob, IUserContext } from '../../interfaces';
import { FaMoneyBill } from 'react-icons/fa';
import { BsFillClockFill } from 'react-icons/bs';

import JobDetail from './JobDetail';
import { MapContainer, TileLayer, Popup, Marker } from 'react-leaflet';
import { OpenStreetMapProvider } from 'leaflet-geosearch';
import ReactQuill from 'react-quill';
import EmployerActions from './EmployerActions';
import { AiFillMail, AiOutlineCheck, AiOutlineMail } from 'react-icons/ai';
import { useEffect, useState, useMemo, useContext } from 'react';
import UserActions from './UserActions';
import { AxiosError } from 'axios';
import { http } from '../../helpers/utils';
import { UserContext } from '../../context/user';
interface IJobDetailsProps {
  job: IEmployerJob;
  detailsType: string;
}

const JobDetails = ({ job, detailsType }: IJobDetailsProps) => {
  const { user } = useContext(UserContext) as IUserContext;
  const [error, setError] = useState('');
  const modules = { toolbar: [] };
  const [locationCoords, setLocationCoords] = useState<any>([]);
  const provider = useMemo(() => new OpenStreetMapProvider(), []);

  useEffect(() => {
    const geocode = async () => {
      const results = await provider.search({
        query: job.location,
      });
      if (results.length) {
        //@ts-ignore
        setLocationCoords((prevState) => [...prevState, results[0].bounds[0]]);
      }
    };
    if (job.location) {
      geocode();
    }
  }, [job.location, provider]);

  const applyToJob = async () => {
    try {
      setError('');
      const response = await http.post('/applications/', {
        profileId: user.profileId,
        userId: user.id,
        jobId: job.id,
        jobPosition: job.position,
        jobCompany: job.companyName,
        employerId: job.employerId,
      });
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        console.log(err.response);
        setError(err.response.data.message);
      }
    }
  };

  return (
    <Box
      minH="500px"
      width={['95%', '95%', '550px']}
      border="1px solid"
      borderRadius="8px"
      p="0.5rem"
      borderColor="border.primary"
    >
      {detailsType === 'employer' ? (
        <EmployerActions
          employerId={job.employerId}
          jobId={job.id}
          jobPosition={job.position}
        />
      ) : (
        <UserActions employerId={job.employerId} jobId={job.id} />
      )}
      <Box pt="5rem">
        <Box>
          <Heading color="black.primary" as="h3" fontSize="1.1rem">
            {job.position}
          </Heading>
          <Text my="0.5rem" color="text.primary">
            {job.companyName}
          </Text>
          <Flex>
            <Text>${job.perHour.toString()} per hour</Text>
            <Text ml="0.5rem">{job.availability}</Text>
          </Flex>
          <Box>
            <Text color="text.primary" my="0.5rem" fontSize="0.85rem">
              Posted by {job.firstName} {job.lastName}
            </Text>
          </Box>
          <Box>
            <Text color="text.primary" my="0.5rem" fontStyle="italic" fontSize="0.85rem">
              Posted {job.readableDate}
            </Text>
          </Box>
        </Box>
        <Box
          my="0.5rem"
          borderBottom="1px solid"
          borderColor="border.primary"
          height="1px"
        ></Box>
        <Box>
          <Heading as="h3" fontSize="1.1rem" color="black.primary">
            Job Details
          </Heading>
          <Box my="1rem">
            <JobDetail
              color="green"
              bg="rgba(94, 218, 94, 0.4)"
              title="Salary"
              value={'$' + job.perHour.toString() + ' per hour'}
              iconTwo={undefined}
              iconOne={<FaMoneyBill />}
            />
          </Box>
          <Box my="1rem">
            <JobDetail
              color="green"
              bg="rgba(94, 218, 94, 0.4)"
              title="Job Type"
              value={job.availability}
              iconTwo={<AiOutlineCheck />}
              iconOne={<BsFillClockFill />}
            />
          </Box>
          <Box my="1rem">
            <Flex alignItems="center">
              <AiFillMail />
              <Text ml="1rem">{job.email}</Text>
            </Flex>
          </Box>
        </Box>
        <Box
          my="0.5rem"
          borderBottom="1px solid"
          borderColor="border.primary"
          height="1px"
        ></Box>
        <Heading as="h3" fontSize="1.1rem" color="black.primary">
          Full Job Description
        </Heading>
        <ReactQuill theme="bubble" readOnly modules={modules} value={job.body} />
        <Box
          my="0.5rem"
          borderBottom="1px solid"
          borderColor="border.primary"
          height="1px"
        ></Box>
        <Heading as="h3" fontSize="1.1rem" color="black.primary">
          Location
        </Heading>
        {locationCoords.length > 0 && (
          <MapContainer center={locationCoords[0]} zoom={13} scrollWheelZoom={false}>
            <TileLayer
              attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
              url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            <Marker position={locationCoords[0]}>
              <Popup>{job.location}</Popup>
            </Marker>
          </MapContainer>
        )}
        <Box
          my="0.5rem"
          borderBottom="1px solid"
          borderColor="border.primary"
          height="1px"
        ></Box>
        {error && (
          <Text color="red" my="1rem" fontSize="0.85rem">
            {error}
          </Text>
        )}
        {detailsType === 'user' && (
          <Box>
            <Button onClick={applyToJob} colorScheme="teal">
              Apply Now
            </Button>
          </Box>
        )}
      </Box>
    </Box>
  );
};

export default JobDetails;
