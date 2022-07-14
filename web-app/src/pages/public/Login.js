import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { requestAuth } from '../../redux/slices/auth/authSlice';
import AuthForm from '../../modules/auth/AuthForm';
// import { setStorageAuthData, setStorageRole, setStorageTokenAccess } from '../../storage/authStorage';
import ModalCustom from '../../shared/components/modalCustom/ModalCustom';
import AuthLayout from '../../modules/auth/AuthLayout';
import { MESSAGE_BAD_CREDENTIALS, MESSAGE_BAD_CREDENTIALS_SPANISH, MESSAGE_ERROR_SERVER_SPANISH } from '../../util/constants/messageErrorConstans';

const Login = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { responseData, responseMessage } = useSelector(state => state.auth);
  const [loading, setLoading] = useState(false);
  const [openModal, setOpenModal] = useState(false);
  const [messageModal, setMessageModal] = useState("");
  const [isChaning, setIsChaning] = useState(false);
  const [values, setValues] = useState({
    usernameOrEmail: '',
    password: '',
    roleId: 1,
  });

  const handleInputChange = (e) => {
    setValues({
      ...values,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    //setUsername(values.usernameOrEmail);
    setIsChaning(!isChaning);
    setLoading(true);
    dispatch(requestAuth({ authDto: values }));
  }

  useEffect(() => {
    // console.log("1")
    // console.log(loading);
    // console.log("2")
    // console.log(openModal);

    if (responseData.token) {
      setLoading(false);
      setOpenModal(false);

      //setStorageAuthData(responseData.user);
      //setStorageTokenAccess(responseData.token);
      //setStorageRole(responseData.role)

      navigate("/app/home");
    } else {
      // setLoading(false);
      if(responseMessage === MESSAGE_BAD_CREDENTIALS) {
        setMessageModal(MESSAGE_BAD_CREDENTIALS_SPANISH)
        setOpenModal(true);
      } 
      // else if (responseMessage === MESSAGE_ERROR_SERVER_SPANISH) {
      //   setMessageModal(responseMessage)
      //   setOpenModal(true);
      // }
    }
  }, [responseData, responseMessage, navigate, isChaning]);

  return (
    <>
      <AuthLayout>
        <AuthForm
          handleInputChange={handleInputChange}
          handleSubmit={handleSubmit}
          values={values}
          loading={loading}
        />
      </AuthLayout>

      <ModalCustom
        open={openModal}
        setOpen={setOpenModal}
        icon="error"
        title="Error!"
        message={messageModal}
      />
    </>
  )
}

export default Login