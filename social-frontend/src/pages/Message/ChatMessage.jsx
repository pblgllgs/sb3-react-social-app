import React from "react";

const ChatMessage = () => {
  return (
    <div className={`flex ${true ? "justify-start" : "justify-end"} text-white`}>
      <div
        className={`p-1 ${
          true ? "rounded-md" : "px-5 rounded-full"
        } bg-[#191c29]`}
      >
        {
            true && <img className="w-[12rem] h-[17rem object-cover rounded-md" src="https://images.pexels.com/photos/1054666/pexels-photo-1054666.jpeg?auto=compress&cs=tinysrgb&w=300" alt="elephant" />
        }
        <p className={`${true ? "py-2" : "py-1"}`}>message...</p>
      </div>
    </div>
  );
};

export default ChatMessage;
