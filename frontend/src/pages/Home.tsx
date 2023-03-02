import { Box, Heading, ListItem, UnorderedList } from '@chakra-ui/react';

const Home = () => {
  return (
    <Box minH="100vh">
      <Box className="home-context">
        <Heading as="h1">Welcome to Joblet</Heading>
      </Box>

      <Box className="home-area">
        <UnorderedList className="home-circles">
          <ListItem></ListItem>
          <ListItem></ListItem>
          <ListItem></ListItem>
          <ListItem></ListItem>
          <ListItem></ListItem>
          <ListItem></ListItem>
          <ListItem></ListItem>
          <ListItem></ListItem>
          <ListItem></ListItem>
          <ListItem></ListItem>
        </UnorderedList>
      </Box>
    </Box>
  );
};

export default Home;
