import { red } from "@mui/material/colors";
import { CardHeader, Avatar, Button } from "@mui/material";

const PopularUSerCard = () => {
  return (
    <div>
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
            R
          </Avatar>
        }
        action={
          <Button size="small">Follow</Button>
        }
        title="pblgllgs"
        subheader="@pblgllgs"
      />
    </div>
  );
};

export default PopularUSerCard;
