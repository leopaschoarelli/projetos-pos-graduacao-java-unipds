import { Suspense } from "react";
import { PostType } from "@/src/app/types";
import Posts from "@/src/components/Posts";

const getPosts = async () => {
  const data = await fetch('https://api.vercel.app/blog');

  const posts: PostType[] = await data.json();

  return posts;
}

export default function Page() {
    // Don't await the data fetching function
    const posts = getPosts();

    return (
        <Suspense fallback={<div>Loading...</div>}>
            <Posts posts={posts} />
        </Suspense>
    );
}