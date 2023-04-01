import { Box, Button, Flex, FormLabel, Textarea, Text } from '@chakra-ui/react';
import { AxiosError } from 'axios';
import { useContext, useState } from 'react';
import { AiFillStar } from 'react-icons/ai';
import { useLocation, useNavigate } from 'react-router-dom';
import Header from '../components/Header';
import { UserContext } from '../context/user';
import { http } from '../helpers/utils';
import { IUserContext } from '../interfaces';

const WriteReview = () => {
  const navigate = useNavigate();
  const { user } = useContext(UserContext) as IUserContext;
  const location = useLocation();
  const [rating, setRating] = useState(0);
  const [review, setReview] = useState('');
  const [error, setError] = useState('');

  const handleOnMouseOver = (index: number) => {
    setRating(index);
  };
  const handleOnMouseLeave = () => {
    if (rating === 1) {
      setRating(0);
      return;
    }
    setRating(rating);
  };

  const writeReview = async () => {
    try {
      setError('');
      const response = await http.post('/reviews/', {
        rating,
        text: review,
        employerId: location.state.employerId,
        userId: user.id,
      });
      navigate('/joblet');
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        if (err.response.status === 400) {
          setError(err.response.data.message);
        }
      }
    }
  };

  return (
    <Box minH="100vh">
      <Box mt="5rem">
        <Header icon={<AiFillStar />} title="Write a Review" text="Review an employer" />
      </Box>
      <Box mx="auto" width={['95%', '95%', '400px']}>
        <Box my="2rem">
          <Flex justifyContent="center">
            {[...Array(5)].map((star, index) => {
              index += 1;
              return (
                <Box
                  fontSize="1.5rem"
                  onMouseEnter={() => handleOnMouseOver(index)}
                  onMouseLeave={handleOnMouseLeave}
                  key={index}
                >
                  <AiFillStar color={index <= rating ? 'orange' : 'black'} />
                </Box>
              );
            })}
          </Flex>
        </Box>
        {error.length > 0 && (
          <Text color="red" fontSize="0.85rem" textAlign="center">
            {error}
          </Text>
        )}
        <Box>
          <FormLabel color="text.primary">Your Review</FormLabel>
          <Textarea onChange={(e) => setReview(e.target.value)} value={review}></Textarea>
        </Box>
        <Box my="3rem" display="flex" justifyContent="center">
          <Button onClick={writeReview} width="90%" colorScheme="teal">
            Submit
          </Button>
        </Box>
      </Box>
    </Box>
  );
};

export default WriteReview;
