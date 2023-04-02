import { Box, Flex, Text } from '@chakra-ui/react';
import { IReview } from '../../interfaces';
import { FaUserCircle } from 'react-icons/fa';
import { AiFillStar } from 'react-icons/ai';
interface IReviewProps {
  review: IReview;
}

const Review = ({ review }: IReviewProps) => {
  return (
    <Box
      width={['95%', '95%', '400px']}
      border="1px solid"
      borderColor="border.primary"
      p="0.5rem"
      borderRadius="8px"
    >
      <Flex alignItems="center">
        <Box mr="0.5rem" fontSize="1.5rem" color="text.primary">
          <FaUserCircle />
        </Box>
        <Box>
          <Text>
            {review.firstName} {review.lastName}
          </Text>
        </Box>
      </Flex>
      <Box mt="1rem">{review.companyName}</Box>
      <Flex my="1.5rem">
        {[...Array(5)].map((star, index) => {
          return (
            <Box>
              <AiFillStar color={review.rating > index ? 'orange' : 'black'} />
            </Box>
          );
        })}
      </Flex>
      <Box>
        <Text wordBreak="break-all">{review.text}</Text>
      </Box>
    </Box>
  );
};

export default Review;
