import { Box, Text, Button, Flex } from '@chakra-ui/react';
import { AxiosError } from 'axios';
import { useContext, useEffect, useState } from 'react';
import { AiFillStar } from 'react-icons/ai';
import { UserContext } from '../context/user';
import { http } from '../helpers/utils';
import { IRetrieveReviewsResponse, IReview, IUserContext } from '../interfaces';
import Header from '../components/Header';
import Review from '../components/Review';

const CompanyReviews = () => {
  const { user } = useContext(UserContext) as IUserContext;
  const [page, setPage] = useState(0);
  const [direction, setDirection] = useState('next');
  const [totalPages, setTotalPages] = useState(0);
  const [reviews, setReviews] = useState<IReview[]>([]);

  useEffect(() => {
    const retrieveReviews = async () => {
      try {
        const response = await http.get<IRetrieveReviewsResponse>(
          `/reviews/?page=-1&size=3&direction=next`
        );
        console.log(response);
        setPage(response.data.page);
        setTotalPages(response.data.totalPages);
        setReviews(response.data.reviewDtos);
      } catch (err: unknown | AxiosError) {
        if (err instanceof AxiosError && err.response) {
          return;
        }
      }
    };
    retrieveReviews();
  }, []);

  const paginateReviews = async (dir: string) => {
    try {
      if (page === totalPages && dir !== 'prev') {
        return;
      }
      setDirection(dir);
      const response = await http.get<IRetrieveReviewsResponse>(
        `/reviews/?page=${page}&size=3&direction=${dir}`
      );
      setPage(response.data.page);
      setTotalPages(response.data.totalPages);
      setReviews(response.data.reviewDtos);
    } catch (err: unknown | AxiosError) {
      if (err instanceof AxiosError && err.response) {
        return;
      }
    }
  };

  return (
    <Box>
      <Box mt="5rem">
        <Header
          icon={<AiFillStar />}
          title="Company Reviews"
          text="See what people are saying."
        />
      </Box>
      <Box width={['95%', '95%', '590px']} mx="auto">
        <Flex alignItems="center" flexDir="column">
          {reviews.map((review) => {
            return <Review key={review.id} review={review} />;
          })}
        </Flex>
        {reviews.length > 0 && (
          <Flex justifyContent="center" mt="3rem" alignItems="center">
            {page + 1 > 1 && (
              <Button onClick={() => paginateReviews('prev')}>Prev</Button>
            )}
            <Box>
              <Text mx="1rem">
                {page + 1} of {totalPages}
              </Text>
            </Box>
            {page + 1 !== totalPages && (
              <Button onClick={() => paginateReviews('next')}>Next</Button>
            )}
          </Flex>
        )}
      </Box>
    </Box>
  );
};

export default CompanyReviews;
