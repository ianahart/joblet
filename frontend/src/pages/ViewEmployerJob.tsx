import { Box } from '@chakra-ui/react';
import { useParams } from 'react-router-dom';
const ViewEmployerJob = () => {
  const params = useParams();
  console.log(params);
  return <Box>View Employer Job</Box>;
};

export default ViewEmployerJob;
