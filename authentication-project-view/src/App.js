import {Route, Routes} from "react-router-dom";
import Login from "./pages/Login";
import SignUp from "./pages/Signup";
import Private from "./pages/Private";
import Admin from "./pages/Admin";

function App() {
    return (
        <div>
            <Routes>
                <Route path={"/login"} element={<Login/>}/>
                <Route path={"/signup"} element={<SignUp/>}/>
                <Route path={"/"} element={<Private/>}/>
                <Route path={"/admin/page"} element={<Admin/>}/>
            </Routes>
        </div>
    );
}
