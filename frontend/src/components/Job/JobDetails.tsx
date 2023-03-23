import { Box, Flex, Heading, Text } from '@chakra-ui/react';
import { IEmployerJob } from '../../interfaces';
import { FaMoneyBill } from 'react-icons/fa';
import { BsFillClockFill } from 'react-icons/bs';

import JobDetail from './JobDetail';
import { MapContainer, TileLayer, Popup, Marker } from 'react-leaflet';
import { OpenStreetMapProvider } from 'leaflet-geosearch';
import ReactQuill from 'react-quill';
import EmployerActions from './EmployerActions';
import { AiOutlineCheck } from 'react-icons/ai';
import { useEffect, useState, useMemo } from 'react';
interface IJobDetailsProps {
  job: IEmployerJob;
  detailsType: string;
}
// if detailsType === user show apply now, favorite, not interested

const JobDetails = ({ job, detailsType }: IJobDetailsProps) => {
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

  return (
    <Box
      minH="500px"
      width={['95%', '95%', '550px']}
      border="1px solid"
      borderRadius="8px"
      p="0.5rem"
      borderColor="border.primary"
    >
      {detailsType === 'employer' && (
        <EmployerActions employerId={job.employerId} jobId={job.id} />
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
              <Popup>
                A pretty CSS3 popup. <br /> Easily customizable.
              </Popup>
            </Marker>
          </MapContainer>
        )}
        <Box
          my="0.5rem"
          borderBottom="1px solid"
          borderColor="border.primary"
          height="1px"
        ></Box>
      </Box>
    </Box>
  );
};

export default JobDetails;
