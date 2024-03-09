import { Routes, Route } from "react-router-dom";
import "./App.css";
import Authentication from "./pages/Authentication/Authentication";
import HomePage from "./pages/HomePage/HomePage";
import Message from "./pages/Message/Message";
import { useSelector,useDispatch } from "react-redux";
import { useEffect } from "react";
import { getProfileAction } from "./Redux/Auth/auth.action";

function App() {
  const user = useSelector((store) => store.auth.user);
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  useEffect(() => {
    dispatch(getProfileAction(jwt));
  }, [jwt,dispatch]);

  return (
    <div className="">
      <Routes>
        <Route
          path="/*"
          element={user ? <HomePage /> : <Authentication />}
        />
        <Route path="/message" element={<Message />} />
        <Route path="/*" element={<Authentication />} />
      </Routes>
    </div>
  );
}

export default App;
