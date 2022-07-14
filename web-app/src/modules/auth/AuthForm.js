import React from 'react'
import ButtonLoading from '../../shared/components/buttonLoading/ButtonLoading';
import InputCustom from '../../shared/components/inputCustom/InputCustom';
import InputPassword from '../../shared/components/inputPassword/InputPassword';

const AuthForm = (props) => {
  const { handleSubmit, handleInputChange, values, loading } = props;

  return (
    <>
      <form onSubmit={handleSubmit}>
        <div className="my-3">
          <InputCustom
            width="w-full"
            type="text"
            name="usernameOrEmail"
            label="username"
            onChange={handleInputChange}
            value={values.usernameOrEmail}
          />
        </div>
        <div className="my-3">
          <InputPassword
            width="w-full"
            name="password"
            label="password"
            onChange={handleInputChange}
            value={values.password}
          />
        </div>
        <div className="my-3">
          <ButtonLoading
            loading={loading}
            type="submit"
            width="w-full"
            textSize="text-md"
            fontWeight="font-semibold"
          >
            Iniciar sesión
          </ButtonLoading>
        </div>
      </form>
    </>
  )
}

export default AuthForm