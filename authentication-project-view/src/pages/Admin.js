import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

export default function Admin() {

    const accessToken = localStorage.getItem("access-token");
    const bearerToken = "Bearer " + accessToken;
    const [UserList, setUserList] = useState([]);
    const navigator = useNavigate();
    const apiAddr = `https://muxplorer.shop`;
    useEffect(() => {
        axios.get(apiAddr + `/api/v1/admin/find/user`, {
            headers: {
                "Authorization": bearerToken
            }
        })
            .then(response => {
                setUserList(response.data.members);
            })
            .catch(error => {
                console.error(error);
                alert("권한이 없습니다. 이용이 불가합니다");
                navigator("/");
            })
    }, []);

    const onSuspendSubmitHandler = (event) => {
        const day = prompt("몇일을 정지하시겠습니까?", 0);
        let data = {
            userId: event,
            day: day
        }
        axios.post(apiAddr + `/api/v1/admin/suspend/user`, data, {
            headers: {
                "Authorization": bearerToken
            }
        })
            .then(response => {
                if (response.data === "SUCCESS") {
                    alert("회원 정지에 성공했습니다");
                    window.location.reload();
                }
            })
            .catch(error => {
                console.error(error.response.data.message);
                alert(error.response.data.message);
                window.location.reload();
            })
    }

    const onDeleteSubmitHandler = (event) => {
        let data = {
            userId: event
        }
        axios.post(apiAddr + `/api/v1/admin/delete/user`, data, {
            headers: {
                "Authorization": bearerToken
            }
        })
            .then(response => {
                if (response.data === "SUCCESS") {
                    alert("회원 삭제에 성공했습니다");
                    window.location.reload();
                }
            })
            .catch(error => {
                console.error(error.response.data.message);
                alert(error.response.data.message);
                window.location.reload();
            })
    }

    return (
        <div className="flex items-center justify-center min-h-screen bg-[#5865F2]">
            <div className="w-full max-w-7xl p-8 space-y-3 bg-black rounded-lg shadow-md overflow-auto">
                <h1 className="text-2xl font-bold text-white text-center">유저 목록</h1>
                <div className="space-y-4">
                    <div className="grid grid-cols-7 gap-3 text-sm text-gray-400 bg-gray-800 p-2 rounded-lg">
                        <span className="font-medium">ID</span>
                        <span className="font-medium">이메일</span>
                        <span className="font-medium">이름</span>
                        <span className="font-medium">전화번호</span>
                        <span className="font-medium">정지일</span>
                        <span className="font-medium">유저 정지</span>
                        <span className="font-medium">유저 삭제</span>
                    </div>
                    <ul className="grid grid-cols-1 gap-4 p-4 rounded-lg bg-gray-800">
                        {UserList.map((user) => (
                            <li key={user.userId} className="grid grid-cols-7 gap-5 text-sm text-gray-400">
                                <span>{user.userId}</span>
                                <span className="max-w-sm overflow-hidden text-overflow: ellipsis">{user.email}</span>
                                <span>{user.name}</span>
                                <span>{user.phone}</span>
                                <span>{user.suspendAt}</span>
                                <button
                                    className="px-2 py-2 text-sm font-medium text-white bg-red-600 rounded-md hover:bg-red-700 focus:outline-none focus:ring focus:ring-red-300"
                                    type="button"
                                    onClick={() => onSuspendSubmitHandler(user.userId)}>
                                    사용자 정지
                                </button>
                                <button
                                    className="px-4 py-2 text-sm font-medium text-white bg-red-600 rounded-md hover:bg-red-700 focus:outline-none focus:ring focus:ring-red-300"
                                    type="button"
                                    onClick={() => onDeleteSubmitHandler(user.userId)}>
                                    사용자 삭제
                                </button>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        </div>
    );
};