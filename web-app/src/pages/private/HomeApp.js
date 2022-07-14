import { useState } from 'react';
import InputCustom from '../../shared/components/inputCustom/InputCustom';

const HomeApp = () => {

  const [form, setForm] = useState({
    first_name: '',
    role: '',
    game: '',
    country: '',
  });

  const handleInputChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(form);
  }

  return (
    <div className="container mx-auto bg-red-200">
      <form onSubmit={handleSubmit}>

        <div className="grid grid-cols-12 gap-2 pb-2">
          <div className="col-span-12 sm:col-span-6 md:col-span-3">
            <InputCustom
              type="text"
              name="first_name"
              label="Nombre"
              onChange={handleInputChange}
              value={form.first_name}
            />
          </div>
          <div className="col-span-12 sm:col-span-6 md:col-span-3">
            <InputCustom
              type="text"
              name="role"
              label="Rol"
              onChange={handleInputChange}
              value={form.role}
            />
          </div>
          <div className="col-span-12 sm:col-span-6 md:col-span-3">
            <InputCustom
              type="text"
              name="game"
              label="Juego"
              onChange={handleInputChange}
              value={form.game}
            />
          </div>
          <div className="col-span-12 sm:col-span-6 md:col-span-3">
           
          </div>
        </div>
      </form>

    </div>
  )
}

export default HomeApp