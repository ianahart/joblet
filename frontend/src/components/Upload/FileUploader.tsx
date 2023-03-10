import { Box, Input, Image, Text } from '@chakra-ui/react';
import { useState } from 'react';
import pdfIcon from '../../images/pdf-icon.jpg';
import { AiOutlineUpload } from 'react-icons/ai';

interface IFileUploaderProps {
  handleFileUpload: (file: File) => void;
  fileName: string;
}

const FileUploader = ({ fileName, handleFileUpload }: IFileUploaderProps) => {
  const [error, setError] = useState('');
  const [isDragging, setIsDragging] = useState(false);

  const handleOnChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files === null) return;
    const file: File = e.target.files[0];
    handleFileChange(file);
  };

  const handleFileChange = (file: File) => {
    setError('');
    if (file.size > 2000000) {
      setError('File size exceeds 2MB.');
      return;
    }
    setIsDragging(false);
    handleFileUpload(file);
  };

  const handleOnDragEnter = (e: React.DragEvent<HTMLDivElement>) => {
    setIsDragging(true);
  };

  const handleOnDragLeave = (e: React.DragEvent<HTMLDivElement>) => {
    setIsDragging(false);
  };

  return (
    <Box
      onDragEnter={handleOnDragEnter}
      onDragLeave={handleOnDragLeave}
      position="relative"
      width="100%"
    >
      <Box
        className="dropzone"
        display="flex"
        alignItems="center"
        height="100px"
        width="100%"
      >
        <Input
          onChange={handleOnChange}
          type="file"
          opacity="0"
          position="absolute"
          height="100%"
          width="100%"
          top="0px"
          zIndex="5"
          left="0"
        />
        {!isDragging ? (
          <>
            <Box>
              <Image width="60px" height="60px" src={pdfIcon} alt="pdf-icon" />
            </Box>
            <Text wordBreak="break-all" color="text.primary" fontSize="0.8rem">
              {fileName}
            </Text>
          </>
        ) : (
          <Box
            className="rise"
            color="text.primary"
            fontSize="2rem"
            display="flex"
            width="100%"
            justifyContent="center"
          >
            <AiOutlineUpload />
          </Box>
        )}
      </Box>
      {error && (
        <Text color="error.primary" fontSize="0.85rem">
          {error}
        </Text>
      )}
    </Box>
  );
};

export default FileUploader;
