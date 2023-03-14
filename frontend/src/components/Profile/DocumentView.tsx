import { Box } from '@chakra-ui/react';
import { useLocation } from 'react-router-dom';
import { Document } from 'react-pdf';

const DocumentView = () => {
  const location = useLocation();
  console.log(location?.state.resume);
  return (
    <Box minH="100vh">
      <object
        type="application/pdf"
        width="100%"
        height="1000"
        data={location?.state.resume}
      ></object>
    </Box>
  );
};

export default DocumentView;
