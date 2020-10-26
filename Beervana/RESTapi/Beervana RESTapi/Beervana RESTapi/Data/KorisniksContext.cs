using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Threading.Tasks;
using Beervana_RESTapi.Controllers;
using Beervana_RESTapi.Models;
using MySqlConnector;

namespace Beervana_RESTapi.Data
{
    public class KorisniksContext
    {
        public AppDb Db { get; }

        public KorisniksContext(AppDb db)
        {
            Db = db;
        }

        public async Task<Korisnik> FindOneAsync(int id)
        {
            using var cmd = Db.Connection.CreateCommand();
            cmd.CommandText = @"SELECT * FROM `korisnik` WHERE `id_korisnik` = @id";
            cmd.Parameters.Add(new MySqlParameter
            {
                ParameterName = "@id",
                DbType = DbType.Int32,
                Value = id,
            });
            var result = await ReadAllAsync(await cmd.ExecuteReaderAsync());
            return result.Count > 0 ? result[0] : null;
        }

        public async Task<List<Korisnik>> LatestPostsAsync()
        {
            using var cmd = Db.Connection.CreateCommand();
            cmd.CommandText = @"SELECT * FROM `korisnik` ORDER BY `id_korisnik` DESC LIMIT 10;";
            return await ReadAllAsync(await cmd.ExecuteReaderAsync());
        }

        public async Task DeleteAllAsync()
        {
            using var txn = await Db.Connection.BeginTransactionAsync();
            using var cmd = Db.Connection.CreateCommand();
            cmd.CommandText = @"DELETE FROM `korisnik`";
            await cmd.ExecuteNonQueryAsync();
            await txn.CommitAsync();
        }

        private async Task<List<Korisnik>> ReadAllAsync(DbDataReader reader)
        {
            var posts = new List<Korisnik>();
            using (reader)
            {
                while (await reader.ReadAsync())
                {
                    var post = new Korisnik(Db)
                    {
                        IdKorisnik = reader.GetInt32(0),
                        IdClanstvo= reader.GetInt32(1),
                        IdUloga = reader.GetInt32(2),
                    };
                    posts.Add(post);
                }
            }
            return posts;
        }
    }
}